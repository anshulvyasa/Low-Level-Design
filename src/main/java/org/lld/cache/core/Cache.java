package org.lld.cache.core;

import org.lld.cache.evictionpolicy.EvictionAlgorithm;
import org.lld.cache.executors.KeyBasedExecutors;
import org.lld.cache.storage.interfaces.CacheStorage;
import org.lld.cache.storage.interfaces.DBStorage;
import org.lld.cache.writepolicy.WritePolicy;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class Cache<K,V> {
    private DBStorage<K,V> dbStorage;
    private CacheStorage<K,V> cacheStorage;
    private EvictionAlgorithm<K> evictionAlgorithm;
    private WritePolicy<K,V> writePolicy;
    private KeyBasedExecutors keyBasedExecutors;

    public Cache(DBStorage<K,V> dbStorage,CacheStorage<K,V> cacheStorage,EvictionAlgorithm<K> evictionAlgorithm,WritePolicy<K,V> writePolicy,int numOfExecutor){
         this.dbStorage=dbStorage;
         this.cacheStorage=cacheStorage;
         this.evictionAlgorithm=evictionAlgorithm;
         this.writePolicy=writePolicy;
         this.keyBasedExecutors=new KeyBasedExecutors(numOfExecutor);
    }

    public CompletableFuture<V> accessData(K key){
        return keyBasedExecutors.submitTask(key,()->{
            try{
                if(!cacheStorage.exist(key)) throw new Exception("This Key:"+key+" doesn't exist in cache");

                evictionAlgorithm.keyAccessed(key);
                return cacheStorage.get(key);
            }
            catch (Exception e){
                throw new CompletionException(e);
            }
        });
    }

    public CompletableFuture<Void> updateData(K key,V value){
        return keyBasedExecutors.submitTask(key,()->{
           try{
              if(cacheStorage.exist(key)){
                  System.out.println("The Key+"+key+" is updated"+cacheStorage.size()+" "+cacheStorage.capacity());
                  writePolicy.write(key,value,cacheStorage,dbStorage);
                  evictionAlgorithm.keyAccessed(key);
              }
              else{
                 if(cacheStorage.size()>=cacheStorage.capacity()){
                     K evictKey= evictionAlgorithm.evictKey();

                     if(evictKey!=null){
                         int currentIndex= keyBasedExecutors.getExecutorIndexForKey(key);
                         int evictedIndex= keyBasedExecutors.getExecutorIndexForKey(evictKey);

                         if(currentIndex==evictedIndex){
                             cacheStorage.remove(evictKey);
                         }
                         else{
                             CompletableFuture<Void> removalFuture= keyBasedExecutors.submitTask(evictKey,()->{
                                 try{
                                     cacheStorage.remove(evictKey);
                                     return null;
                                 }catch (Exception e){
                                     throw  new CompletionException(e);
                                 }
                             });
                             removalFuture.join();
                         }
                     }
                 }

                 writePolicy.write(key,value,cacheStorage,dbStorage);
                 evictionAlgorithm.keyAccessed(key);
              }

              return null;
           }
           catch (Exception e){
               throw new CompletionException(e);
           }
        });
    }

    public void shutdown(){
        keyBasedExecutors.shutdown();
    }

}
