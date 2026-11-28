package org.lld.cache.doublelinkedlist;

import lombok.Getter;
import lombok.Setter;

@Getter
public class DoublyLinkedListNode<K>{
    private final K value;
    @Setter
    private DoublyLinkedListNode<K> prev;
    @Setter
    private DoublyLinkedListNode<K> next;

    public DoublyLinkedListNode(K value){
        this.value=value;
        prev=null;
        next=null;
    }
}
