package org.lld.pub_sub.interfaces;

import org.lld.pub_sub.utilities.Message;

public interface ISubscriber {
    String getId();
    void onMessage(Message message) throws InterruptedException;
}
