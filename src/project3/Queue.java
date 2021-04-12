package project3;

/**
 * Queue class.
 * Data structure in which the first items in are the first items out
 * @version Winter 2021
 * 
 * */

public class Queue<E> extends SLL<E>{

    /**
     * Constructor for Queue.
     */
    public Queue(){
        super();
    }

    /**
     * add an item to the end of the queue. This item can only be dequeued from the queue after all 
     * items in front of it have been dequeued. 
     * @param E element - the element to be added to the queue
     */
    public void enqueue(E element){
        add(element);
    }

    /**
     * removes the item from the front of the queue, and returns the value of that item. 
     * @return E - the item just removed from the front of the queue. 
     */
    public E dequeue(){
        return remove(0);
    }

    /**
     * returns the value at the front of the queue without removing that value from the queue. 
     * @return E - current value at the front of the queue. 
     */
    public E peek(){
        return get(0);
    }

    /**
     * returns the current number of items in the queue
     * @return int - the size of the queue
     */
    @Override
    public int size(){
        return super.size();
    }

    /**returns a boolean to tell if the queue is empty or not. 
     * @return boolean - true if the queue has no values in it. false otherwise
     */
    public boolean isEmpty(){
        if (size() == 0){
            return true;
        }
        return false;
    }
}
