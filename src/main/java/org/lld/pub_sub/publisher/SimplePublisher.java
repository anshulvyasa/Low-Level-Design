package org.lld.pub_sub.publisher;

import org.lld.pub_sub.controllers.KafkaController;
import org.lld.pub_sub.interfaces.IPublisher;
import org.lld.pub_sub.utilities.Message;

public class SimplePublisher implements IPublisher {
    private final String id;
    private KafkaController kafkaController;

    public  SimplePublisher(String id,KafkaController kafkaController){
        this.id=id;
        this.kafkaController=kafkaController;
    }

    @Override
    public String getId() {
        return id;
    }

    // Todo: Add Kafka Controller Here
    @Override
    public void publish(String tropicId, Message message) throws IllegalArgumentException {
        kafkaController.publish(tropicId,message,this);
    }
}
