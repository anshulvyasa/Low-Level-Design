package org.lld.cache.executors;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class KeyBasedExecutors {
    private final int noOfExecuter;
    private final ExecutorService[] executors;

    public KeyBasedExecutors(int noOfExecuter){
        this.noOfExecuter=noOfExecuter;
        this.executors=new ExecutorService[noOfExecuter];

        for(int i=0;i<noOfExecuter;i++){
            executors[i]= Executors.newSingleThreadExecutor();
        }
    }

    public <T> CompletableFuture<T> submitTask(Object key, Supplier<T> task){
        int idx=getExecutorIndexForKey(key);
        ExecutorService executor=executors[idx];
        return CompletableFuture.supplyAsync(task,executor);
    }

    public int getExecutorIndexForKey(Object key){
        return Math.abs(Objects.hash(key)%noOfExecuter);
    }

    public void shutdown(){
        for(ExecutorService executors:executors){
            executors.shutdown();
        }
    }
}
