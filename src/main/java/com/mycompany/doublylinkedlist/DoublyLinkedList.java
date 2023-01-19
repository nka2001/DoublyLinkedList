package com.mycompany.doublylinkedlist;

/**
 *
 * @author nicka
 */
public class DoublyLinkedList {

    public static void main(String[] args) {
        DoublyLinkedListDS<String> dll = new DoublyLinkedListDS<>();
        
        dll.append("Test 1");
        dll.append("Test 2");
        dll.prepend("Test 3");
        
        System.out.println(dll.size());
       
        
        System.out.println(dll.toString());//test 3 should come first
        
        System.out.println(dll.get(0));//should print out test 3
        
        
        
        
    }
}
