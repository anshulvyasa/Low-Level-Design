package org.lld.rate_limiter.models;

import org.lld.rate_limiter.enums.UserType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class User {
    public int id;
    public UserType userType;
}
