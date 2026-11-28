package org.lld.pub_sub.controllers;

import org.lld.pub_sub.interfaces.ISubscriber;
import org.lld.pub_sub.tropic.Tropic;
import org.lld.pub_sub.utilities.Message;
import org.lld.pub_sub.utilities.TropicSubscriber;

public class SubscriberController implements Runnable {
    TropicSubscriber tropicSubscriber;

    public SubscriberController(TropicSubscriber tropicSubscriber){
        this.tropicSubscriber=tropicSubscriber;
    }

    @Override
    public void run() {
        Tropic tropic=tropicSubscriber.getTropic();
        ISubscriber subscriber=tropicSubscriber.getSubscriber();

        while(true){
            Message message=null;
            synchronized (tropicSubscriber){
                if(tropicSubscriber.getOffSet().get() >= tropic.getMessages().size()){
                    try{
                        tropicSubscriber.wait();
                    }
                    catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                int messageOffSet=tropicSubscriber.getOffSet().getAndIncrement();
                message=tropic.getMessages().get(messageOffSet);
            }

            try {
               subscriber.onMessage(message);
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
