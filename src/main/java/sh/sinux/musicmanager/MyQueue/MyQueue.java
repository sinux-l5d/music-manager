package sh.sinux.musicmanager.MyQueue;

import sh.sinux.musicmanager.MyLinkedList.MyLinkedList;

public class MyQueue <T> {
    private final MyLinkedList<T>queue;
    public MyQueue() {
        queue = new MyLinkedList<>();
    }

    /**
     * Check if the queue is empty
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Get the size of the queue
     * @return the size of the queue
     */
    public int size() {
        return queue.size();
    }

    /**
     * Add data to the queue
     * @param data the data to be added
     */
    public void enqueue(T data) {
        queue.add(data);
    }

    /**
     * Remove and return the data from the queue
     * @return The oldest data added to the queue
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new QueueEmptyException();
        }
        // Get the first data in the queue
        T data = queue.get(0);
        // To remove it
        queue.remove(data);
        // and return it
        return data;
    }

    /**
     * Get the data from the queue without removing it
     * @return The oldest data added to the queue
     */
    public T peek() {
        if (isEmpty()) {
            throw new QueueEmptyException();
        }
        return queue.get(0);
    }

    public String toString() {
        return queue.toString();
    }
}
