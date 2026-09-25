package org.lld.rate_limiter.factory;

import org.lld.rate_limiter.enums.RateLimiterType;
import org.lld.rate_limiter.limiter.FixedWindowRateLimiter;
import org.lld.rate_limiter.limiter.RateLimiter;
import org.lld.rate_limiter.limiter.SlidingWindowRateLimiter;
import org.lld.rate_limiter.limiter.TokenBucketRateLimiter;
import org.lld.rate_limiter.models.RateLimiterConfig;

public class RateLimiterfactory {
    public static RateLimiter createRateLimiter(RateLimiterType type, RateLimiterConfig config){
       return switch (type){
           case RateLimiterType.FIXEDWINDOW -> new FixedWindowRateLimiter(config);
           case RateLimiterType.SLIDINGWINDOW -> new SlidingWindowRateLimiter(config);
           case RateLimiterType.TOKENBUCKET -> new TokenBucketRateLimiter(config);
           default -> throw new IllegalArgumentException("Unexpected Input Given");
       };
    }
}
