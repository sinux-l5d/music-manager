package sh.sinux.musicmanager.MyLinkedList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T>{

    private Node<T> first;
    private Node<T> last;
    private int size;

    public MyLinkedList(){
        first = null;
        last = null;
    }

    public boolean isEmpty()
    {
        return first == null;
    }

    public void add(T data){
        Node newNode = new Node(data);
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
    public void addFirst(T data){
        Node newNode = new Node(data);
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
    public void add(int pos, T data){
        if(pos > size || pos < 0){
            throw new IndexOutOfBoundsException();
        }else if(pos == 0){
            addFirst(data);
        }else{
            Node newNode = new Node(data);
            if(pos == size){
                last.next = newNode;
                newNode.prev = last;
                last = newNode;
            }else{
                Node current = first;
                for(int i = 0; i < pos; i++){
                    current = current.next;
                }
                Node predecessor = current.prev;
                newNode.prev = predecessor;
                predecessor.next = newNode;
                newNode.next = current;
                current.prev = newNode;
            }
            size++;
        }
    }
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
            // Return the data stored within the node at that position
            return current.data;
        }
    }
    public int size(){
        return size;
    }

    @Override
    public Iterator iterator() {
        return new MyLinkedListIterator<T>();
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

        public boolean hasNext(){
            return next != null;
        }

        public boolean hasPrev(){
            return prev != null;
        }
    }
    private class MyLinkedListIterator<T> implements ListIterator<T>{
        // Track what number element is next
        private int nextIndex;
        // Track what element we last returned
        private Node<T> lastReturned;
        // We need to track the NEXT element in the list
        private Node<T> next;

        public MyLinkedListIterator(){
            next = (Node<T>) first;
            lastReturned = null;
            nextIndex = 0;
        }

        // Provide the index of the next element in the iterator
        @Override
        public int nextIndex()
        {
            return nextIndex;
        }

        @Override
        // Provide the index of the element we just returned in the iterator
        // This will be -1 if we haven't move the iterator from the start of the list
        public int previousIndex()
        {
            return nextIndex-1;
        }

        // Check if there's another element after the one we're currently on
        @Override
        public boolean hasNext()
        {
            // If the position we're at is below the size, then
            // we still have information left in the list
            return nextIndex < size;
        }
        // Check if there's an element BEFORE the one we're currently on
        @Override
        public boolean hasPrevious(){
            // if the position we're at is above 0, then there is a node before this one
            return nextIndex > 0;
        }

        // Return the next element in the list
        // Firstly, confirm there is another element in the list (in case the
        // calling code did not check this first). If no further data exists, throw an exception

        // If there are elements left in the list, then move the lastReturned pointer to the
        // current "next" element

        // Once that is done, we move our next pointer onto the following element in the list
        // and increase the nextIndex value (i.e. increasing our position count within the list)

        // Lastly, we return the data we retrieved in step one (i.e. what's within lastReturned)
        // This method returns an Object because the Iterator interface defines it as an Object
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
                lastReturned = (Node<T>) last;
                // Move our pointer within the list back a slot
                next = (Node<T>) last;
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

        // Methods for amending the list via the iterator
        // As these are marked as OPTIONAL in the API, we don't have to write code
        // for them but we do have to include them
        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public void set(Object e)
        {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public void add(Object e)
        {
            throw new UnsupportedOperationException("Not supported.");
        }
    }
}
