## Data Structure suitability (Simon)

### Linked list

#### Order of operation

- `isEmpty()`: O(1)
- `add(T data)`: O(1)
- `addFirst(T data)`: O(1)
- `add(int pos
- `get(int pos)`: O(n)
- `remove(T data)`: O(n)
- `size()`: O(1)
- `set(int pos
- `toString()`: O(n)
- `selectionSort(Comparator<T> comparator)`: O(n²) (to be moved to MyLinkedList)
- `Node.*`: O(1), constant operations (getters and setters)
- `MyLinkedListIterator.*`: O(1), constant operations (getters and setters)

#### Data cost

- `isEmpty()`: No extra data, accessing a variable
- `add(T data)`: Node creation, constant data cost
- `addFirst(T data)`: Node creation, constant data cost
- `add(int pos, T data)`: Node creation, constant data cost
- `get(int pos)`: No extra data, accessing existing elements
- `remove(T data)`: No extra data, updating pointers
- `size()`: No extra data, accessing a variable
- `set(int pos, T data)`: No extra data, updating existing element
- `toString()`: StringBuilder, linear data cost (O(n))
- `selectionSort(Comparator<T> comparator)`: In-place sorting, constant data cost
- `Node.*`: No extra data, constant data cost (getters and setters)
- `MyLinkedListIterator.*`: No extra data, constant data cost (getters and setters)

#### Benchmark

#### Strengths
#### Weaknesses

### Hash map

#### Order of operation

- `put(K key, V value)`: O(1), average case (worst case O(n) if hash collisions)
- `get(K key)`: O(1), average case (worst case O(n) if hash collisions)
- `get(K key, V defaultValue)`: O(1), average case (worst case O(n) if hash collisions)
- `containsKey(K key)`: O(1), average case (worst case O(n) if hash collisions)
- `keySet()`: O(n)
- `values()`: O(n)
- `remove(K key)`: O(1), average case (worst case O(n) if hash collisions)
- `toString()`: O(n)
- `size()`: O(1)
- `Entry.*`: O(1), constant operations (getters and setters)

#### Data cost

- `put(K key, V value)`: Entry creation, constant data cost
- `get(K key)`: No extra data, accessing existing elements
- `get(K key, V defaultValue)`: No extra data, accessing existing elements
- `containsKey(K key)`: No extra data, accessing existing elements
- `keySet()`: Array creation, linear data cost (O(n))
- `values()`: Array creation, linear data cost (O(n))
- `remove(K key)`: No extra data, updating array and size
- `toString()`: StringBuilder, linear data cost (O(n))
- `size()`: No extra data, accessing a variable
- `Entry.*`: No extra data, constant data cost (getters and setters)

#### Benchmark
#### Strengths
#### Weaknesses

### Data structure recommendation

### Playlist structures
