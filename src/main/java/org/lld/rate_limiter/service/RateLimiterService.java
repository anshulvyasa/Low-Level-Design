package org.lld.rate_limiter.service;

import org.lld.rate_limiter.enums.RateLimiterType;
import org.lld.rate_limiter.enums.UserType;
import org.lld.rate_limiter.factory.RateLimiterfactory;
import org.lld.rate_limiter.limiter.RateLimiter;
import org.lld.rate_limiter.models.RateLimiterConfig;
import org.lld.rate_limiter.models.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiterService {
    Map<UserType, RateLimiter> ratelimiters;

    public RateLimiterService(){
       ratelimiters=new ConcurrentHashMap<>();

       ratelimiters.put(UserType.FREE, RateLimiterfactory.createRateLimiter(RateLimiterType.FIXEDWINDOW,new RateLimiterConfig(10,60)));
       ratelimiters.put(UserType.PREMIUM,RateLimiterfactory.createRateLimiter(RateLimiterType.SLIDINGWINDOW,new RateLimiterConfig(100,60)));
    }

    public boolean allowUser(User user) throws Exception{
        RateLimiter limiter=ratelimiters.get(user.userType);

        if(limiter==null){
            throw new IllegalAccessException("Not Allowded");
        }

        return  limiter.allowUser(user.id);
    }

}
