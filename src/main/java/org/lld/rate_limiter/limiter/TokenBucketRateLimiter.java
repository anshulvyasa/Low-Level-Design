package org.lld.rate_limiter.limiter;

import org.lld.rate_limiter.enums.RateLimiterType;
import org.lld.rate_limiter.models.RateLimiterConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class TokenBucketRateLimiter extends RateLimiter{
    Map<Integer,Integer> tokenBucket=new ConcurrentHashMap<>();
    Map<Integer,Long> lastRefillTime=new ConcurrentHashMap<>();

    public TokenBucketRateLimiter(RateLimiterConfig config){
        super(RateLimiterType.TOKENBUCKET,config);
    }

    @Override
    public boolean allowUser(int userId){
        long now=System.currentTimeMillis()/1000;
        AtomicBoolean allowed=new AtomicBoolean(false);

        tokenBucket.compute(userId,(id,count)->{
            int tokens=refillTokenBucket(id,now);

            if(tokens>0){
                allowed.set(true);
                return tokens-1;
            }

            return tokens;
        });

        return allowed.get();
    }

    private int refillTokenBucket(int userId,long now){
        lastRefillTime.putIfAbsent(userId,now);

        double fillRate = (double) config.maxRequests / config.windowSizeinSeconds;
        long timeElasped=now-lastRefillTime.get(userId);

        int refill=(int)(fillRate*timeElasped);
        int currentToken=tokenBucket.getOrDefault(userId,config.maxRequests);

        currentToken=Math.min(config.maxRequests,currentToken+refill);

        if(refill>0) lastRefillTime.put(userId,now);

        return  currentToken;

    }
}
