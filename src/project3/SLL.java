/**
 * Project 3 Part 1 - Single Linked List
 * Creates a Single Linked List Data structure
 * 
 * @author Nicole Dudas, Jaqueline Poland, Kyle Knapp
 * @version Winter 2021
 * 
 * */
package project3;

import java.util.NoSuchElementException;

public class SLL<E> {

    //the first element in the linked list
    private Node<E> head;

    //the size of the linked list
    private int size;

    public SLL(){
        head = null;
        size = 0;
    }

    /**
     * Node objects hold data and link to next items in the linked list
     */
    private class Node<E> {

        public E data;
        public Node<E> next;

        public Node(E data){
            this.data = data;
        }
    }

    /**
     * returns size of the linked list
     */
    public int size(){
        return this.size;
    }

    /**
     * clears all items in the linked list
     */
    public void clear(){
        head = null;
        size = 0;
    }

    /**
     * add an item into the linked list at a specified index
     * @param index- the index at which to add a new element
     * @param element - the object (data) to be stored in the node at that
     * linked list element
     * @throws IllegalArgumentException- thrown when the index is out of bounds for linked list
     */
    public void add(int index, E element){

        //throws an exception if the index is out of bounds
        if (index < 0 || index > size){
            throw new IllegalArgumentException("Index must be within 0 - (last index + 1)");
        }

        /*if it is the first element, makes this the head. Otherwise, sets this element as the next value
        for the previous node*/
        Node<E> tmp = new Node(element);
        if(index == 0){
            tmp.next = head;
            head = tmp;
        } else {
            Node<E> prior = getNode(index - 1);
            tmp.next = prior.next;
            prior.next = tmp;
        }
        size++;
    }

    /**
     * removes an element at a specified index fromt he linked list
     * @param index - the index at which to remove an item
     * @return E - the data (object) stored at the removed element
     * @throws NoSuchElementException - thrown when the SLL size is 0 or index is out of
     * bounds for SLL size
     */
    public E remove(int index){

        //if the index is out of bounds or the SLL is empty, throws an exception
        if (index >= this.size || index < 0){
            throw new NoSuchElementException();
        }
        if (this.size <= 0){
            throw new NoSuchElementException();
        }

        //saves node to be removed and its data
        Node<E> toRemove = getNode(index);
        E removedData = toRemove.data;

        /*if the node is the head, sets something else to the head, so this node will 
        be deleted because nothing points to it*/
        if(index == 0){
            head = getNode(index + 1);
        } 

        /*if this node is the last node, set the next of the node before it to be null, so that 
        nothing will point to the last node and it will be deleted*/
        else if(index == this.size - 1){
            Node<E> prior = getNode(index - 1);
            prior.next = null;
        }

        /*if the node is neither first or last, cause the node before it to "point" to the node after
        it using it's next field, so the specified node will have nothing pointing to it and will be 
        deleted*/
        else {
            Node<E> prior = getNode(index - 1);
            Node<E> after = getNode(index + 1);
            prior.next = after;
        }
        size--;
        return removedData;
    }

    /**
     * returns a boolean indicating if a node of the linked list contains
     * the specified element (object)
     * @param element - the element to search for
     * @return Boolean - true if the element is in the linked list, false otherwise
     */
    public Boolean contains(E element){
        Node<E> current = head;
        int i = 0;

        /*loops through the list, and returns true if the memory address of any node's element matches
        the memory address of the parameter element
         */
        while (i < this.size){
            if (current.data == element){
                return true;
            }
            ++i;
            current = current.next;
        }
        return false;
    }

    /**
     * switched out the element (data) within a node at a specified index for a new 
     * element.
     * @param index - the index at which you would like to edit the element
     * @param element - the new element to replace the previoius one with
     * @return E - (object) the element which was previously stored in the node at that element
     * @throws IllegalArgumentException - thrown when the index is out of bounds for the 
     * SLL size
     */
    public E set(int index, E element){
        if (index >= this.size || index < 0){
            throw new IllegalArgumentException("Index is out of Bounds");
        }
        Node<E> current = getNode(index);
        E previousData = current.data;
        current.data = element;

        return previousData;
    }

    /**
     * creates an array from the linked list
     * @return Object[] - an array containing all of the elements from your
     * linked list nodes
     */
    public Object[] toArray(){
        Object[] nodeArray = new Object[this.size];
        Node<E> current = head;
        int i = 0;

        while (i < this.size){
            nodeArray[i] = current.data;
            ++i;
            current = current.next;
        }
        return nodeArray;
    }

    /**
     * returns the element (data) stored at a specified index of your linked list
     * @param index - the index to retrieve data from
     * @return - the data (element) stored at that index
     * @throws IllegalArgumentException - thrown when the index is out of bounds for 
     * the SLL size
     */
    public E get(int index){
        if (index >= 0 && index < this.size){
            return getNode(index).data;
        } else{
            throw new IllegalArgumentException("Index not within linked list");
        }
    }

    /**
     * returns the Node object at a specified index 
     * @param index - the index to retrieve a node from
     * @return - the node at the specified index
     */
    private Node<E> getNode(int index){
        int count = 0;
        Node<E> current = head;
        while(count < index){
            current = current.next;
            count++;
        }
        return current;
    }

    /**
     * adds a node containing the specified data to the end of your linked list
     * @param data - the data to be contained in the new linked list node
     * @return - true, indicating that the new node was able to be added
     */
    public boolean add(E data){
        Node<E> tmp = new Node(data);
        tmp.next = null;
        if(head == null){
            head = tmp;
        } else {
            Node<E> current = head;
            while(current.next != null){
                current = current.next;
            }
            current.next = tmp;
        }

        size++;
        return true;
    }

    public static void main(String[] args){
        SLL<Integer> nums = new SLL<Integer>();
        SLL<String> names = new SLL<String>();

        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(1);
        nums.add(4);
        nums.add(42);
        nums.add(1701);

        names.add("Mr. W.");
        names.add("Joel");
        names.add("Jacque");
        names.add("Aaron");
        names.add("Mariah");
        names.add("Kyle");
        names.add("Connor");
        names.add("Phillip");
        names.add("Nicole");

        System.out.println("Nums has " + nums.size() + " elements.");
        System.out.println("Names has " + names.size() + " elements.");

        for(int i=0; i<nums.size(); ++i){
            System.out.println(nums.get(i));
        }

        for(int i=0; i<names.size(); ++i){
            System.out.println(names.get(i));
        }
    }
}