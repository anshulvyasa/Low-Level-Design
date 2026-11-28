package org.lld.cache;

import org.lld.cache.core.Cache;
import org.lld.cache.evictionpolicy.EvictionAlgorithm;
import org.lld.cache.evictionpolicy.LRUEvictionPolicy;
import org.lld.cache.storage.cachestorage.InMemoryCacheStorage;
import org.lld.cache.storage.dbstorage.SimpleDBStorage;
import org.lld.cache.storage.interfaces.CacheStorage;
import org.lld.cache.storage.interfaces.DBStorage;
import org.lld.cache.writepolicy.WritePolicy;
import org.lld.cache.writepolicy.WriteThroughCache;

public class Main {
    public static void main(String[] args) {
        CacheStorage<String,String> cacheStorage=new InMemoryCacheStorage<>(5);
        DBStorage<String,String > dbStorage=new SimpleDBStorage<>();
        EvictionAlgorithm<String> evictionAlgorithm=new LRUEvictionPolicy<>();
        WritePolicy<String,String> writePolicy=new WriteThroughCache<>();

        Cache<String,String> cache=new Cache<>(dbStorage,cacheStorage,evictionAlgorithm,writePolicy,12);

        try{
            cache.updateData("A", "Apple").join();
            cache.updateData("B", "Banana").join();
            cache.updateData("C", "Cherry").join();
            cache.updateData("D", "Durian").join();
            cache.updateData("E", "Elderberry").join();

            // At this point, the in-memory cache is at capacity.
            // The next write will trigger eviction (of the least recently used key) from the cache.
            cache.updateData("F", "Fig").join();

            try {
                String valueA = cache.accessData("A").join();
                System.out.println("A: " + valueA);
            } catch(Exception e) {
                System.out.println("A is evicted or not found in cache.");
            }

            String valueF = cache.accessData("F").join();
            System.out.println("F: " + valueF);

            cache.updateData("B", "Blueberry").join();
            String valueB = cache.accessData("B").join();
            System.out.println("B: " + valueB);

            cache.shutdown();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
