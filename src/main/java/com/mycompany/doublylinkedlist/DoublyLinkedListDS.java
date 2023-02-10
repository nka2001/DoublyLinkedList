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

        Node<T> addBack = new Node<>();//create a new node
        addBack.data = addMe;//set data in node

        addBack.next = dummy;//make the next of this new node point to dummy
        addBack.prev = dummy.prev;//make the previous point to dummys previous

        dummy.prev.next = addBack;//set dummys previou's next (essentially what was the tail to point to the new node
        dummy.prev = addBack;//set dummys previous to point to the new node

        n++;//increment the number of elements

    }

    /**
     * prepend method will add a node at the beginning of the list
     *
     * @param addMe
     */
    public void prepend(T addMe) {

        Node<T> addFront = new Node<>();//create a new node
        addFront.data = addMe;//set the data in the node

        addFront.prev = dummy;//make the new nodes previous point to dummy 
        addFront.next = dummy.next;//make the next pointer of new node point to the old head

        dummy.next.prev = addFront;//make the old heads previous point to the new node
        dummy.next = addFront;//make the new head (dummy.next) point to the new node

        n++;//increment the number of elements
    }

    public T setDataInNodeAt(int position, T newData) {

        Node<T> current = dummy.next;
        current.data = (T) dummy.next.data;

        return current.data;
    }

    /**
     * getNode method will return a nodes data at a given position
     *
     * @param position
     * @return
     */
    public T get(int position) {

        if (position >= n) {//if the position asked for is greater than the total size of the list, then
            throw new NoSuchElementException();//throw an exception
        }

        Node<T> current = dummy.next;//create a node to start at the head of the LL
        T returnMe = current.data;//returnMe is the data at whatever position

        for (int i = 0; i < n - 1; i++) {//loop through the LL, starting at the head

            if (i == position) {//if i is the position, then 
                returnMe = current.data;//set the return data
            }
            current = current.next;//keep moving through the list until the end is reached
        }

        return returnMe;//return the data
    }

    /**
     * returns the number of elements in the list
     *
     * @return
     */
    public int size() {
        return n;//return the number of elements in the linked list
    }

    /**
     * getNode method, will return the node of the given position, used by
     * removeAt
     *
     * @param position
     * @return
     */
    private Node<T> getNode(int position) {

        Node<T> current = dummy.next;//start at the head

        for (int i = 0; i < position; i++) {//loop through the list until the position is met

            current = current.next;//move the node through

        }

        return current;//return the node

    }

    /**
     * remove method, will look for the removeMe, then remove it, returns true
     * if the item was removed
     *
     * @param removeMe
     * @return
     */
    public boolean remove(T removeMe) {

        Node<T> removeNode = dummy.next; //start at head
        removeNode.data = dummy.next.data;

        //case 1: LL is empty, do nothing
        if (removeNode.data.equals("Default")) {//default is dummy's data
            return false;
        }
        //case 2: just call indexOf and removeAt

        int index = indexOf(removeMe);

        if (index > 0) {//if the index returned by indexOf is negative (-1) then return false, it means removeMe isnt in the LL
            removeAt(index);//otherwise, call removeAt on the index
            return true;//and return true
        } else {
            return false;//false is returned if indexOf returns -1 (meaning removeMe wasnt found)
        }

    }

    /**
     * indexOf method will return the first index of an item, otherwise -1
     *
     * @param findMe
     * @return the first index of findMe
     */
    public int indexOf(T findMe) {

        Node<T> findIndex = dummy.next; //start at the head of the LL
        findIndex.data = dummy.next.data;

        int index = -1;//this is returned, -1 if the index is not found

        for (int i = 0; i < n; i++) {//iterate through the linked list
            if (findMe.equals(findIndex.data)) {//if the data at the current node is the same as findMe, then thats the index
                index = i;//set the index 
                return index;//return it
            }
            findIndex = findIndex.next;//otherwise, move through the linked list
        }

        return index;//return the final index

    }

    /**
     * lastIndexOf method, will start at the tail of the linked list, and
     * iterate until findMe is found
     *
     * @param findMe
     * @return
     */
    public int lastIndexOf(T findMe) {

        Node<T> findLast = dummy.prev;//start at the tail of the linked list
        findLast.data = dummy.prev.data;

        int index = -1;//the index is what is returned, -1 if findMe is not found

        for (int i = n - 1; i > 0; i++) {//iterate through the linked list
            if (findMe.equals(findLast.data)) {//if the current nodes data equals findMe then
                index = i;//set its index
                return index;//and return it 
            }
            findLast = findLast.prev;//otherwise keep moving through the linked list
        }
        return index;//finally, return the index

    }

    /**
     * removeAt will remove the element at a given position
     *
     * @param position
     * @return
     */
    public T removeAt(int position) {

        T data = dummy.next.data;//data of the node being removed (start at the head)

        if (position >= n) {//if the position given is bigger than the list then
            throw new NoSuchElementException();//throw an exception
        } else {

            Node<T> removeMe = getNode(position);//get the node being removed

            data = removeMe.data;//set the data to be returned

            removeMe.prev.next = removeMe.next;//pointer placement, make removeMe's previous' next pointer point to the node that comes after the node being removed
            removeMe.next.prev = removeMe.prev;//pointer placement, make removeMe's next's previous pointer point to the node that comes before the node being removed

            n--;//decrement the number of elements in the list

        }

        return data;//return the data of the node that was removed

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
