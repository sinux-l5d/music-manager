package sh.sinux.musicmanager.MyHashMap;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import sh.sinux.musicmanager.MyLinkedList.MyLinkedList;

import java.lang.reflect.Array;

/**
 * @author Simon Leonard
 */
@State(Scope.Benchmark)
public class MyHashMap<K, V> {
    /**
     * the maximum number of linked lists in the array
     * The larger this number, the fewer collisions you'll have (depending on K's hash function)
     */
    private static final int MAX_SLOTS = 10;

    /**
     * the number of key/value pairs stored in the hash map
     */
    private int size;
    /**
     * the array of linked lists that will store the key/value pairs
     */
    private final MyLinkedList<Entry<K, V>>[] data;
    private final Class<K> kClass;
    private final Class<V> vClass;

    /**
     * Create a new hash map
     * @param kClass The class of the keys to store, used to instantiate new arrays
     * @param vClass The class of the values to store, used to instantiate new arrays
     */
    public MyHashMap(Class<K> kClass, Class<V> vClass) {
        this.kClass = kClass;
        this.vClass = vClass;
        data = new MyLinkedList[MAX_SLOTS];
        size = 0;
    }

    /**
     * Put a new key-value pair into the hash map
     * If the key already exists, overwrite the value and return the old value
     *
     * @param key   The key to insert
     * @param value The value to insert
     * @return The old value if the key already exists, null otherwise
     */
    public V put(K key, V value) {
        int hash = Math.abs(key.hashCode());
        int destinationIndex = hash % data.length;

        // Instantiate a new list if there's nothing there
        if (data[destinationIndex] == null) {
            MyLinkedList<Entry<K, V>> list = new MyLinkedList<>();
            data[destinationIndex] = list;
        } else {
            // If there's already something in this slot, check if the key already exists
            // replace the value if it does
            for (Entry<K, V> currentEntry : data[destinationIndex]) {
                if (currentEntry.key.equals(key)) {
                    // We want to return old value
                    V old = currentEntry.getValue();
                    currentEntry.setValue(value);
                    return old;
                }
            }
        }
        // In both cases, we want to add the new entry to the end of the list and increment the size
        size++;
        Entry<K, V> newEntry = new Entry<>(key, value);
        data[destinationIndex].add(newEntry);
        return null;
    }

    /**
     * Get the value associated with a key
     * @param key The key to search for
     * @return The value associated with the key, or null if the key doesn't exist
     */
    public V get(K key) {
        // get which slot to check
        int hash = Math.abs(key.hashCode());
        int destinationIndex = hash % data.length;

        // check if there's a list stored in this slot
        if (data[destinationIndex] != null) {
            // if so, check the elements in the list for a match to this key
            for (Entry<K, V> currentEntry : data[destinationIndex]) {
                if (currentEntry.key.equals(key)) {
                    return currentEntry.getValue();
                }
            }
        }
        // if not found
        return null;
    }

    /**
     * Get the value associated with a key, or a default value if the key doesn't exist
     * @param key The key to search for
     * @param defaultValue The value to return if the key doesn't exist
     * @return The value associated with the key, or the default value if the key doesn't exist.
     */
    public V get(K key, V defaultValue) {
        var value = get(key);
        return value == null ? defaultValue : value;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public K[] keySet() {
        K[] keys = (K[]) Array.newInstance(kClass, size);
        int index = 0;
        for (MyLinkedList<Entry<K, V>> list : data) {
            if (list != null) {
                for (Entry<K, V> entry : list) {
                    keys[index] = entry.getKey();
                    index++;
                }
            }
        }
        return keys;
    }

    public V[] values() {
        V[] values = (V[]) Array.newInstance(vClass, size);
        int index = 0;
        for (MyLinkedList<Entry<K, V>> list : data) {
            if (list != null) {
                for (Entry<K, V> entry : list) {
                    values[index] = entry.getValue();
                    index++;
                }
            }
        }
        return values;
    }


    public boolean remove(K key) {
        int hash = Math.abs(key.hashCode());
        int destinationIndex = hash % data.length;
        if (data[destinationIndex] != null) {
            for (Entry<K, V> currentEntry : data[destinationIndex]) {
                if (currentEntry.key.equals(key)) {
                    data[destinationIndex].remove(currentEntry);
                    size--;
                    if (data[destinationIndex].isEmpty()) {
                        data[destinationIndex] = null;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        for (MyLinkedList<Entry<K, V>> list : data) {
            if (list != null) {
                for (Entry<K, V> entry : list) {
                    sb.append(entry.key).append(": ").append(entry.value).append(", ");
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * Return the count of how many key/value pairs have been added to the map
     */
    public int size() {
        return size;
    }

    /**
     * A simple key/value pair
     * @param <K> The key type
     * @param <V> The value type
     */
    private static class Entry<K, V> {
        private final K key;
        private V value;

        /**
         * Create a new key/value pair
         *
         * @param key   The key
         * @param value The value
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V newValue) {
            this.value = newValue;
        }
    }
}
