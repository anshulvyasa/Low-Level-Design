package org.lld.pub_sub.utilities;

import lombok.Getter;
import org.lld.pub_sub.interfaces.IPublisher;
import org.lld.pub_sub.tropic.Tropic;

@Getter
public class TropicPublisher {
    private final Tropic tropic;
    private final IPublisher publisher;

    public TropicPublisher(Tropic tropic,IPublisher publisher){
        this.tropic=tropic;
        this.publisher=publisher;
    }
}
