package org.lld.cache.writepolicy;

import org.lld.cache.storage.interfaces.CacheStorage;
import org.lld.cache.storage.interfaces.DBStorage;

public interface WritePolicy<K,V> {
    void write(K key, V value, CacheStorage<K,V> cacheStorage, DBStorage<K,V> dbStorage) throws Exception;
}
