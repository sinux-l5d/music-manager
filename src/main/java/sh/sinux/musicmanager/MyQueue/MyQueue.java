package sh.sinux.musicmanager.MyQueue;

import sh.sinux.musicmanager.MyLinkedList.MyLinkedList;

public class MyQueue <T> {
    private MyLinkedList<T>queue;
    public MyQueue() {
        queue = new MyLinkedList<>();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    public void enqueue(T data) {
        queue.add(data);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new QueueEmptyException("Queue is empty.");
        }
        T data = queue.get(0);
        //possibly create removeLast() that in worst case it will be O(1) instead of O(n)

        queue.remove(data);
        return data;
    }

    public T peek() {
        if (isEmpty()) {
            throw new QueueEmptyException("Queue is empty.");
        }
        return queue.get(0);
    }
}
