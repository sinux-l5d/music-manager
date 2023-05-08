package sh.sinux.musicmanager.MyLinkedList;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @author Zi Jie Lee
 */

@State(Scope.Benchmark)
public class MyLinkedList<T> implements Iterable<T>{

    private Node<T> first;
    private Node<T> last;
    private int size;

    public MyLinkedList(){
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Checks if the list is empty
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty()
    {
        return first == null;
    }

    /**
     * Adds an element to the end of the list
     */
    public void add(T data){
        Node<T> newNode = new Node<>(data);
        if(isEmpty()){
            first = newNode;
            last = newNode;
        }
        else{
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        size++;
    }

    /**
     * Adds an element to the beginning of the list
     * @param data
     */
    public void addFirst(T data){
        Node<T> newNode = new Node<>(data);
        if(isEmpty()){
            first = newNode;
            last = newNode;
        }
        else{
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        }
        size++;
    }
    /**
     * Adds an element to the list at the specified position
     * @param pos the position to add the element at
     * @param data the data to add
     */
    public void add(int pos, T data){
        if(pos > size || pos < 0){
            throw new IndexOutOfBoundsException();
        }else if(pos == 0){
            addFirst(data);
        }else{
            Node<T> newNode = new Node<>(data);
            if(pos == size){
                last.next = newNode;
                newNode.prev = last;
                last = newNode;
            }else{
                Node<T> current = first;
                for(int i = 0; i < pos; i++){
                    current = current.next;
                }
                Node<T> predecessor = current.prev;
                newNode.prev = predecessor;
                predecessor.next = newNode;
                newNode.next = current;
                current.prev = newNode;
            }
            size++;
        }
    }

    /**
     * Gets element at the specified position
     * @param pos the position to get the element from
     * @return the element at the specified position
     */
    public T get(int pos){
        // If the list is empty, or the position supplied is outside the range
        if(isEmpty() || pos >= size || pos < 0){
            // Throw an exception
            throw new IndexOutOfBoundsException();
        }
        // If they want the data in the first element, return that
        else if(pos == 0){
            return first.data;
        }
        // If they want the data in the last element, return that
        else if(pos == size-1){
            return last.data;
        }
        else{
            // Loop through the list until you reach the supplied position
            Node<T> current = first;
            for(int i = 0; i < pos; i++){
                current = current.next;
            }

            return current.data;
        }
    }

    /**
     * Removes an element from the list
     * @param data the element to remove
     * @return true if the element was removed, false otherwise
     */
    public boolean remove(T data){
        if(isEmpty())
            return false;
        Node<T> current = first;
        while(current != null){
            if(current.data.equals(data)){
                if(current == first){
                    first = first.next;

                    if (first != null)
                        first.prev = null;
                    else
                        last = null;
                }
                else if(current == last){
                    last = last.prev;
                    if (last != null)
                        last.next = null;
                    else
                        first = null;
                }
                else{
                    Node<T> predecessor = current.prev;
                    Node<T> successor = current.next;
                    predecessor.next = successor;
                    successor.prev = predecessor;
                }
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Gets the size of the list
     * @return the size of the list
     */
    public int size(){
        return size;
    }

    /**
     * Updates the data at the specified position
     * @param pos the position to update the data at
     * @param data the new data
     * @return the old data that was replaced
     */
    public T set(int pos, T data) {
        // Check if the position is within the valid range of the list
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }

        // Loop through the list until you reach the desired position
        Node<T> current = first;
        for (int i = 0; i < pos; i++) {
            current = current.next;
        }
        // Store the data of the current node in a temporary variable (old data)
        T temp = current.data;
        // Set the data of the current node to the new data
        current.data = data;
        return temp;
    }

    /**
     * Displays the list in a readable format (one element per line)
     * @return String the list in a readable format
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<T> current = first;
        while(current != null){
            sb.append(current.data.toString());
            sb.append("\n");
            current = current.next;
        }
        return sb.toString();
    }
    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator();
    }


    private static class Node<T>{
        private T data;
        private Node<T> next;
        private Node<T> prev;

        public Node(T data){
            next = null;
            prev = null;
            this.data = data;
        }

        /**
         * Checks if there is a next element in the list
         * @return true if there is a next element, false otherwise
         */
        public boolean hasNext(){
            return next != null;
        }

        /**
         * Checks if there is a previous element in the list
         * @return true if there is a previous element, false otherwise
         */
        public boolean hasPrev(){
            return prev != null;
        }
    }
    private class MyLinkedListIterator implements ListIterator<T> {
        // Track what number element is next
        private int nextIndex;
        // Track what element we last returned
        private Node<T> lastReturned;
        // We need to track the NEXT element in the list
        private Node<T> next;

        public MyLinkedListIterator(){
            next = first;
            lastReturned = null;
            nextIndex = 0;
        }

        // Provide the index of the next element in the iterator

        /**
         * Gets the index of the next element in the iterator
         * @return the index of the next element in the iterator
         */
        @Override
        public int nextIndex()
        {
            return nextIndex;
        }

        @Override
        // Provide the index of the element we just returned in the iterator
        // This will be -1 if we haven't move the iterator from the start of the list
        /**
         * Gets the index of the previous element in the iterator
         * @return the index of the previous element in the iterator
         */
        public int previousIndex()
        {
            return nextIndex-1;
        }

        /**
         * Checks if there is another element after the one we're currently on
         * @return true if there is another element after the one we're currently on, false otherwise
         */
        @Override
        public boolean hasNext()
        {
            return nextIndex < size;
        }

        /**
         * Checks if there is an element before the one we're currently on
         * @return true if there is an element before the one we're currently on, false otherwise
         */
        @Override
        public boolean hasPrevious(){
            return nextIndex > 0;
        }

        /**
         * Gets the next element in the list
         *
         * @throws NoSuchElementException if there is no next element
         * @return the next element in the list
         */
        @Override
        public T next(){
            // Check there is still data left in the list
            if (!hasNext()){
                // If no data remains, throw an exception
                throw new NoSuchElementException();
            }
            // Move the lastReturned pointer to the current slot (as this is what we'll return at the end)
            lastReturned = next;
            // Move the current slot on by one (so it points to the right place for next time)
            next = next.next;
            // Increase the number of elements we've returned
            nextIndex++;
            // Return the data we retrieved at the start
            return lastReturned.data;
        }

        /**
         * Gets the previous element in the list
         *
         * @throws NoSuchElementException if there is no previous element
         * @return the previous element in the list
         */
        @Override
        public T previous()
        {
            // Check if there is any data before this one in the list
            if(!hasPrevious()){
                // If no data exists before this element, throw an exception
                throw new NoSuchElementException();
            }
            // Set the lastReturned variable to contain the current element within next
            // (as this is the next thing to return)
            if(next == null){
                // If we've moved past the end of the list, point our lastReturned
                // variable to the final element (i.e. point it to the end)
                lastReturned = last;
                // Move our pointer within the list back a slot
                next = last;
            }else{
                // If we're not at the end, point it at the link before the current next one
                lastReturned = next.prev;
                // Move our pointer within the list back a slot
                next = next.prev;
            }
            // Decrease our count on where we are in the list
            nextIndex--;
            // Return the information stored in the lastReturned node
            return lastReturned.data;
        }

        /**
         * Remove is not implemented by this iterator
         * @throws UnsupportedOperationException prompt the user that this operation is not supported
         */
        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("Not supported.");
        }

        /**
         * Set is not implemented by this iterator
         * @throws UnsupportedOperationException prompt the user that this operation is not supported
         * @param e the element to set
         */
        @Override
        public void set(Object e)
        {
            throw new UnsupportedOperationException("Not supported.");
        }

        /**
         * Add is not implemented by this iterator
         * @throws UnsupportedOperationException prompt the user that this operation is not supported
         * @param e the element to add
         */
        @Override
        public void add(Object e)
        {
            throw new UnsupportedOperationException("Not supported.");
        }
    }
}
