package org.lld.cache.storage.cachestorage;

import org.lld.cache.storage.interfaces.CacheStorage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCacheStorage<K,V> implements CacheStorage<K,V> {
    private Map<K,V> cache;
    private int capacity;

    public InMemoryCacheStorage(int capacity){
        this.capacity=capacity;
        cache=new ConcurrentHashMap<>();
    }

    @Override
    public void put(K key, V value) throws Exception {
        if(!cache.containsKey(key)&&cache.size()>=capacity) throw  new Exception("Cache Size is full");

        cache.put(key,value);
    }

    @Override
    public V get(K key) throws Exception {
        if(!cache.containsKey(key)) throw new Exception("Data Entry with Key:"+key+" doesn't exist in the cache");
        return cache.get(key);
    }

    @Override
    public boolean exist(K key) throws Exception {
        return cache.containsKey(key);
    }

    @Override
    public void remove(K key) throws Exception {
        if(!cache.containsKey(key)) throw  new Exception("Key:"+key+" doesn't exist for the remove operation");
        cache.remove(key);
    }

    @Override
    public int size() throws Exception {
        return cache.size();
    }

    @Override
    public int capacity() throws Exception {
        return capacity;
    }
}
