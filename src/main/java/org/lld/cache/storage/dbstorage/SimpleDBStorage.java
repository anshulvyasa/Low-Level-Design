package org.lld.cache.storage.dbstorage;

import org.lld.cache.storage.interfaces.DBStorage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleDBStorage<K,V> implements DBStorage<K,V> {
    private Map<K,V> db;

    public SimpleDBStorage(){
        db=new ConcurrentHashMap<>();
    }


    @Override
    public void write(K key, V value) throws Exception {
        db.put(key,value);
    }

    @Override
    public V read(K key) throws Exception {
        if(!db.containsKey(key)) throw new Exception("This Key:"+key+" is not inside the database");
        return db.get(key);
    }

    @Override
    public void delete(K key) throws Exception {
        if(!db.containsKey(key)) throw new Exception("This Key:"+key+" is not inside the database");
        db.remove(key);
    }
}
