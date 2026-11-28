package org.lld.pub_sub.utilities;

import lombok.Getter;
import org.lld.pub_sub.interfaces.ISubscriber;
import org.lld.pub_sub.tropic.Tropic;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class TropicSubscriber {
    private final Tropic tropic;
    private final ISubscriber subscriber;
    private final AtomicInteger offSet;

    public TropicSubscriber(Tropic tropic,ISubscriber subscriber){
        this.tropic=tropic;
        this.subscriber=subscriber;
        offSet=new AtomicInteger(0);
    }
}
