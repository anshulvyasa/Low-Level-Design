package org.lld.cache.doublelinkedlist;

import lombok.Getter;

public class DoublyLinkedList<K> {
    @Getter
    private DoublyLinkedListNode<K> head;
    private DoublyLinkedListNode<K> tail;

    public DoublyLinkedList(){
        this.head=null;
        this.tail=null;
    }

    public void addNodeToTail(DoublyLinkedListNode<K> node){
       if(head==null){
           head=node;
           tail=node;
       }
       else{
           tail.setNext(node);
           node.setPrev(tail);
           tail=node;
       }

       node.setNext(null);
    }

    public void detachNode(DoublyLinkedListNode<K> node){
         if(node==null) return;

         if(node.getPrev()!=null){
             node.getPrev().setNext(node.getNext());
         }
         else{
             head=node.getNext();
         }

         if(node.getNext()!=null){
             node.getNext().setPrev(node.getPrev());
         }
         else{
             tail=node.getPrev();
         }

         node.setPrev(null);
         node.setNext(null);
    }

    public void removeHead(){
        if(head!=null){
            DoublyLinkedListNode<K> node=head;
            head=head.getNext();

            if(head!=null) head.setPrev(null);
            node.setNext(null);
        }
    }
}
