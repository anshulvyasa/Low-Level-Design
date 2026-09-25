package org.lld.rate_limiter.models;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RateLimiterConfig {
    public int maxRequests;
    public int windowSizeinSeconds;
}
