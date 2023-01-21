package com.mycompany.doublylinkedlist;

/**
 *
 * @author nicka
 * @param <T>
 */
public class Node<T> extends DoublyLinkedList {
    
    T data;
    Node<T> prev;
    Node<T> next;
    
    public Node(){
        this.data = (T) "Default";
        this.next = null;
        this.prev = null;
    }
    
    public Node(T data){
        this.data = data;
        this.next = null;
        this.prev = null;
        
    }
  
  
    
    
}
