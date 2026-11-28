package org.lld.pub_sub.interfaces;

import org.lld.pub_sub.utilities.Message;

public interface IPublisher {
    String getId();
    void publish(String tropicId, Message message) throws IllegalArgumentException;
}
