package org.lld.rate_limiter.limiter;

import org.lld.rate_limiter.enums.RateLimiterType;
import org.lld.rate_limiter.models.RateLimiterConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class FixedWindowRateLimiter extends  RateLimiter {
    Map<Integer,Integer> requestCount=new ConcurrentHashMap<>();
    Map<Integer,Long> windows=new HashMap<>();

    public FixedWindowRateLimiter(RateLimiterConfig config){
         super(RateLimiterType.FIXEDWINDOW,config);
    }

    @Override
    public boolean allowUser(int userId){
        long currentWindow=System.currentTimeMillis()/1000/config.windowSizeinSeconds;
        AtomicBoolean allowded=new AtomicBoolean(false);

        requestCount.compute(userId,(id,count)->{
            Long previousWindow=windows.get(userId);

            if(previousWindow==null||previousWindow!=currentWindow){
                windows.put(userId,currentWindow);
                allowded.set(true);
                return 1;
            }

            if(count==null) count=0;
            if(count<config.maxRequests){
                allowded.set(true);
                return count+1;
            }

            return count;
        });


        return allowded.get();
    }
}
