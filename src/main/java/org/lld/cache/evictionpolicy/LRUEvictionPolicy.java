package org.lld.cache.evictionpolicy;

import org.lld.cache.doublelinkedlist.DoublyLinkedList;
import org.lld.cache.doublelinkedlist.DoublyLinkedListNode;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy<K> implements EvictionAlgorithm<K>{
    private DoublyLinkedList<K> dll;
    private Map<K,DoublyLinkedListNode<K>> keyToNodeMap;

    public  LRUEvictionPolicy(){
        dll=new DoublyLinkedList<>();
        keyToNodeMap =new HashMap<>();
    }

    @Override
    public synchronized void keyAccessed(K key) throws Exception {
        if(keyToNodeMap.containsKey(key)){
            DoublyLinkedListNode<K> node=keyToNodeMap.get(key);
            dll.detachNode(node);
            dll.addNodeToTail(node);
        }
        else{
            DoublyLinkedListNode<K> node=new DoublyLinkedListNode<>(key);
            dll.addNodeToTail(node);
            keyToNodeMap.put(key,node);
        }
    }

    @Override
    public synchronized K evictKey() throws Exception {
        DoublyLinkedListNode<K> node=dll.getHead();
        if(node==null) return null;

        K key=node.getValue();
        dll.removeHead();
        keyToNodeMap.remove(key);

        return key;
    }
}
