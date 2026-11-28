package org.lld.pub_sub.subscriber;

import org.lld.pub_sub.interfaces.ISubscriber;
import org.lld.pub_sub.utilities.Message;

public class SimpleSubscriber implements ISubscriber {
    private final String id;

    public SimpleSubscriber(String id){
        this.id=id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void onMessage(Message message) throws InterruptedException {
        System.out.println("Subscriber "+id+" Received "+message.getMessage());
        Thread.sleep(500);
    }
}
