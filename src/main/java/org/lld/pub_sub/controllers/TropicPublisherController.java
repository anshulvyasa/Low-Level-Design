package org.lld.pub_sub.controllers;

import org.lld.pub_sub.utilities.Message;
import org.lld.pub_sub.utilities.TropicPublisher;

public class TropicPublisherController {
    private TropicPublisher tropicPublisher;

    public TropicPublisherController(TropicPublisher tropicPublisher){
        this.tropicPublisher=tropicPublisher;
    }

    public synchronized void publish(Message message,KafkaController kafkaController){

    }
}
