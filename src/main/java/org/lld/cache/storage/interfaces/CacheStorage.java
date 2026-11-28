package org.lld.cache.cachestorage;

public interface CacheStorage<K,V> {
    void put(K key,V value) throws Exception;
    V get(K key) throws Exception;
    boolean exist(K key) throws Exception;
    void remove(K key) throws Exception;
    int size() throws Exception;
    int capacity() throws Exception;
}
