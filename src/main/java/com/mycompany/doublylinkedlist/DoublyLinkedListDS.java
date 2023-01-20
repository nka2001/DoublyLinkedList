package com.mycompany.doublylinkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * doubly linked list class, contains the methods to perform adding, removing,
 * and get operations
 *
 * @author nicka
 * @param <T>
 */
public class DoublyLinkedListDS<T extends Comparable<T>> implements Iterable<T> {

    private int n; //number of elements
    private Node<T> dummy;//dummy node is used to stop adding and removing issues, dummy never gets removed

    /**
     * default constructor, list is empty so initialize n to 0, and then
     * initialize the dummy node.
     */
    public DoublyLinkedListDS() {
        dummy = new Node<>();
        dummy.next = dummy;//make dummys next point to itself
        dummy.prev = dummy;//make dummys previous point to itself
        n = 0;
    }

    /**
     * append method will add a node to the back of the list
     *
     * @param addMe
     */
    public void append(T addMe) {


      Node<T> addBack = new Node<>();
      addBack.data = addMe;
      
      addBack.next = dummy;
      addBack.prev = dummy.prev;
      
      dummy.prev.next = addBack;
      dummy.prev = addBack;
      
      n++;
      
    }

    /**
     * prepend method will add a node at the beginning of the list
     * @param addMe 
     */
    public void prepend(T addMe){
        
        Node<T> addFront = new Node<>();//create a new node
        addFront.data = addMe;//set the data in the node
        addFront.next = dummy.next;//make the next of the new node point to the current head of the list
        dummy.next.prev = addFront;//set the heads previous to point to the new node
        dummy.next = addFront;//set the dummys next to the new node
        n++;//increment the number of nodes
        
    }
    
    public T setDataInNodeAt(int position, T newData){
        
        Node<T> current = dummy.next;
        current.data = (T) dummy.next.data;
        
        
        
        
        
        
        
        
        
        return current.data;
    }
    
    /**
     * getNode method will return a nodes data at a given position
     * @param position
     * @return 
     */
    public T get(int position){
        
        if(position >= n){
            throw new NoSuchElementException();
        }
        
        
        Node<T> current = dummy.next;
        T returnMe = current.data;
        
        for(int i = 1; i < n-1; i++){
            
            if(i == position){
                returnMe = current.data;
            }
            current = current.next;
        }
        
        return returnMe;
    }
    
    /**
     * returns the number of elements in the list 
     * @return 
     */
    public int size(){
        return n;
    }
    
    @Override
    public String toString() {

        Iterator<T> iter = iterator();
        String str = "";
        str += "{ ";
        while (iter.hasNext()) {
            str += iter.next();
            if (iter.hasNext()) {
                str += ", ";
            }
        }
        str += " }";
        return str;
    }

    @Override
    public Iterator<T> iterator() {

        Iterator<T> iter;
        iter = new Iterator<T>() {

            int position = -1;
            Node<T> current = dummy.next; //start at the "head" of the linked list, since we dont look at dummy, look at what comes after dummy

            @Override
            public boolean hasNext() {
                return current != dummy;//if current is not null, then there is another node in the list
            }

            @Override
            public T next() {

                if (!hasNext()) {//if there is another node, then negate, if the list is empty throw an exception
                    throw new NoSuchElementException();
                } else {

                    T data = current.data;//save the current nodes data
                    current = current.next;//move the current node forward one position
                    position++;//increment position

                    return data;//return the data from the current node 

                }

            }

            @Override
            public void remove() {

            }

        };

        return iter;
    }

}
