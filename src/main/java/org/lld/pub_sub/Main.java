package org.lld.pub_sub;

import org.lld.pub_sub.controllers.KafkaController;
import org.lld.pub_sub.publisher.SimplePublisher;
import org.lld.pub_sub.subscriber.SimpleSubscriber;
import org.lld.pub_sub.tropic.Tropic;
import org.lld.pub_sub.utilities.Message;

public class Main {
    public static void main(String[] args) {
        KafkaController kafkaController=new KafkaController();

        Tropic tropic1=kafkaController.createTropic("T1");
        Tropic tropic2=kafkaController.createTropic("T2");

        SimpleSubscriber simpleSubscriber1=new SimpleSubscriber("subscriber1");
        SimpleSubscriber simpleSubscriber2=new SimpleSubscriber("subscriber2");
        SimpleSubscriber simpleSubscriber3=new SimpleSubscriber("subscriber3");

        kafkaController.subscribe(tropic1.getTropicId(),simpleSubscriber1);
        kafkaController.subscribe(tropic1.getTropicId(),simpleSubscriber2);
        kafkaController.subscribe(tropic2.getTropicId(),simpleSubscriber3);


        SimplePublisher simplePublisher1=new SimplePublisher("publisher1",kafkaController);
        SimplePublisher simplePublisher2=new SimplePublisher("publisher2",kafkaController);

        simplePublisher1.publish(tropic1.getTropicId(),new Message("Hello"));
        simplePublisher1.publish(tropic1.getTropicId(),new Message("This is Ansul Vyas"));
        simplePublisher2.publish(tropic2.getTropicId(),new Message("This is T2 Speaking"));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        simplePublisher2.publish(tropic2.getTropicId(), new Message("Message m4"));
        simplePublisher1.publish(tropic1.getTropicId(), new Message("Message m5"));
        // Reset offset for subscriber1 on topic1 (for example, to re-process messages).
        kafkaController.resetOffset(tropic1.getTropicId(), simpleSubscriber1, 0);
        // Allow some time before shutting down.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        kafkaController.shutdown();
    }
}
