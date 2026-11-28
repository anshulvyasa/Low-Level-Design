package org.lld.cache.evictionpolicy;

public interface EvictionAlgorithm<K> {
    void keyAccessed(K key) throws Exception;
    K evictKey() throws Exception;
}
