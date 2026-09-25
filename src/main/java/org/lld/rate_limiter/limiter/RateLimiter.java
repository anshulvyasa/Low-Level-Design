package org.lld.rate_limiter.limiter;

import lombok.AllArgsConstructor;
import org.lld.rate_limiter.enums.RateLimiterType;
import org.lld.rate_limiter.models.RateLimiterConfig;

@AllArgsConstructor
public abstract class RateLimiter {
    protected RateLimiterType type;
    protected RateLimiterConfig config;

    public abstract boolean allowUser(int userId);
}
