package org.lld.pub_sub.tropic;

import lombok.Getter;
import org.lld.pub_sub.utilities.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Tropic {
    @Getter
    private final String tropicId;
    @Getter
    private final String tropicName;
    private final List<Message> messages;

    public Tropic(String tropicId,String tropicName){
        this.tropicId=tropicId;
        this.tropicName=tropicName;
        messages=new ArrayList<>();
    }

    public synchronized void addMessage(Message message){
        messages.add(message);
    }

    public synchronized List<Message> getMessages(){
        return Collections.unmodifiableList(messages);
    }
}
