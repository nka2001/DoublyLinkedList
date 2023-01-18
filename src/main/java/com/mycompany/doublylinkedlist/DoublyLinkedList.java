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
        
        System.out.println(dll.toString());
        
        
    }
}
