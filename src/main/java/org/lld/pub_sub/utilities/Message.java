package org.lld.pub_sub.utilities;

import lombok.Getter;

@Getter
public class Message {
    private final String message;

    public Message(String message){
        this.message=message;
    }
}
