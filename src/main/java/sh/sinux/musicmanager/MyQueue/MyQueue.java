package sh.sinux.musicmanager.MyQueue;

public class MyQueue <T> {
    // Pointer to the first element in the queue
    private Node<T> first;
    // Pointer to the last element in the queue
    private Node<T> last;

    // How many elements are in the queue
    // Increase this when an element is added.
    // Decrease this when an element is removed.
    private int numElements;

    // When a list is started, the first slot is empty
    public MyQueue(){
        first = null;
        last = null;
    }
    //return the size of number of element inserted
    public int size(){
        return numElements;
    }

    // Method to check if the list contains anything
    public boolean isEmpty()
    {
        return first == null;
    }
    //insert element into the queue
    public void enqueue(T element){
        Node<T> newNode = new Node(element);
        // Check if the queue currently has any elements in it
        // If not, set it as the first and last element in the list
        if(isEmpty()){
            first = newNode;
            last = newNode;
        }else{
            // Otherwise, add it in AFTER the last element
            // Connect it on after the current last element
            last.next = newNode;
            // Set the new node to be the last element
            last = newNode;
        }
        numElements++;
    }
    //remove first element in the list
    public T dequeue(){
        // If the queue is empty, throw an exception
        if(isEmpty()){
            throw new QueueEmptyException("Queue is empty.");
        }else{
            // Get the data within the element currently at start of queue
            T data = first.data;
            // Chop the current first element off the queue
            first = first.next;
            // Decrease number of elements in queue
            numElements--;
            // If there are no more elements in the list,
            // Wipe the contents of the last element pointer, too
            if(numElements == 0){
                last = null;
            }
            // Return the data from the original element at start of queue
            return data;
        }
    }

    public T peek(){
        // If the queue is empty, throw an exception
        if(isEmpty()){
            throw new QueueEmptyException("Queue is empty.");
        }else{
            // Return the data within the element currently at start of queue
            return first.data;
        }
    }
    // Generic Node class
    // This class defines the wrapper we use to store data within
    // It MUST contain
    // 1) A variable to store the data
    // 2) A Node pointing to the node after this one (i.e. the next node in the list)
    private static class Node<E>{
        private E data;
        private Node<E> next;

        // Constructor takes in the data to be stored
        // It should set the next node in the list to be null
        // as this node is not connected to anything yet
        public Node(E data){
            next = null;
            this.data = data;
        }

        // Handy method to let us check if there's anything connected to this node
        // Lets us check if this is the last one in the list
        public boolean hasNext(){
            return next != null;
        }
    }
}
