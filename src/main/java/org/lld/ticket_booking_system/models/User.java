package org.lld.ticket_booking_system.models;

import lombok.Getter;

@Getter
public class User {
    private final String userId;
    private final String email;

    public User(String userId,String email){
        this.userId=userId;
        this.email=email;
    }
}
