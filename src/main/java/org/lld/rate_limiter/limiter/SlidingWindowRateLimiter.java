package org.lld.rate_limiter.limiter;

import org.lld.rate_limiter.enums.RateLimiterType;
import org.lld.rate_limiter.models.RateLimiterConfig;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class SlidingWindowRateLimiter extends  RateLimiter {
    Map<Integer, Deque<Long>> requestLog=new ConcurrentHashMap<>();

    public SlidingWindowRateLimiter(RateLimiterConfig config){
         super(RateLimiterType.SLIDINGWINDOW,config);
    }

    @Override
    public boolean allowUser(int userId){
        long now=System.currentTimeMillis();
        AtomicBoolean allowed =new AtomicBoolean(false);

        requestLog.compute(userId,(id,log)->{
           if(log==null) log=new ArrayDeque<>();

           while(!log.isEmpty()&& (now-log.peek()) >= config.windowSizeinSeconds){
               log.poll();
           }

           if(log.size()<config.maxRequests){
               allowed.set(true);
               log.add(now);
           }

           return  log;
        });

        return allowed.get();
    }
}
