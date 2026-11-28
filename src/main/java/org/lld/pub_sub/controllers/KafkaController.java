package org.lld.pub_sub.controllers;

import org.lld.pub_sub.interfaces.IPublisher;
import org.lld.pub_sub.interfaces.ISubscriber;
import org.lld.pub_sub.tropic.Tropic;
import org.lld.pub_sub.utilities.Message;
import org.lld.pub_sub.utilities.TropicSubscriber;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class KafkaController {
    private final Map<String, Tropic> tropicMap;
    private final Map<String, List<TropicSubscriber>> tropicSubscribers;
    private final AtomicInteger tropicCounter;
    private ExecutorService subscriberExecuter;

    public KafkaController(){
        tropicCounter=new AtomicInteger(0);
        tropicMap=new ConcurrentHashMap<>();
        tropicSubscribers=new ConcurrentHashMap<>();
        subscriberExecuter = Executors.newCachedThreadPool();
    }

    public Tropic createTropic(String name){
        String tropicId=String.valueOf(tropicCounter.incrementAndGet());
        Tropic tropic=new Tropic(tropicId,name);

        tropicMap.put(tropicId,tropic);
        tropicSubscribers.put(tropicId,new CopyOnWriteArrayList<>());

        return tropic;
    }

    public void subscribe(String tropicId, ISubscriber subscriber){
         Tropic tropic=tropicMap.get(tropicId);
         if(tropic==null){
             System.err.println("Tropic Doesn't exist");
             return;
         }

        TropicSubscriber tropicSubscriber1=new TropicSubscriber(tropic,subscriber);
         tropicSubscribers.get(tropicId).add(tropicSubscriber1);

         subscriberExecuter.submit(new SubscriberController(tropicSubscriber1));

        System.out.println("Subscriber "+subscriber.getId()+" subscribed to tropic "+tropic.getTropicName());
    }

    public void publish(String tropicId, Message message, IPublisher publisher){
          Tropic tropic=tropicMap.get(tropicId);
          if(tropic==null){
              throw new IllegalArgumentException("Topic with id " + tropicId + " does not exist");
          }

          tropic.addMessage(message);

          // Now notify the Subscriber
          List<TropicSubscriber> tss=tropicSubscribers.get(tropicId);
          for(TropicSubscriber tropicSubscriber:tss){
              synchronized (tropicSubscriber){
                  tropicSubscriber.notify();
              }
          }

        System.out.println("Message:"+message.getMessage()+" Published to Tropic:"+tropic.getTropicName()+" by producer:"+publisher.getId());
    }

    public void resetOffset(String tropicId,ISubscriber subscriber,int newOffset){
        List<TropicSubscriber> tropicSubscribers1=tropicSubscribers.get(tropicId);
        if(tropicId==null){
            System.err.println("Tropic with id:"+tropicId+" doesn't exist");
            return;
        }

        for(TropicSubscriber tropicSubscriber:tropicSubscribers1){
            if(tropicSubscriber.getSubscriber().getId().equals(subscriber.getId())){
                tropicSubscriber.getOffSet().set(newOffset);

                synchronized (tropicSubscriber){
                    tropicSubscriber.notify();
                }

                System.out.println("Reset for Subscriber:"+subscriber.getId()+" for tropic:"+tropicId+" offset is reseted to "+newOffset);

                break;
            }
        }
    }

    public void shutdown(){
        subscriberExecuter.shutdown();

        try{
            if(!subscriberExecuter.awaitTermination(5,TimeUnit.SECONDS)) {
                subscriberExecuter.shutdownNow();
            }
        }
        catch (InterruptedException e){
             subscriberExecuter.shutdownNow();
        }
    }

}
