## Data Structure suitability (Simon)

For timing the operations, I've decided to use the benchmark framework JMH in Average Time mode.

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

During the benchmark, I have a OutOfMemoryError when trying to add elements for the linked list. It used several Gb of memory on my computer.

I although have a benchmark for the get method, which get all the SIZE elements (500 in my case) previously inserted in the setup method.

```
Result "sh.sinux.musicmanager.BenchmarkMyLinkedList.getBenchmark":
  127435.518 ±(99.9%) 2452.389 ns/op [Average]
  (min, avg, max) = (126873.370, 127435.518, 128339.854), stdev = 636.878
  CI (99.9%): [124983.129, 129887.907] (assumes normal distribution)
```

```
Benchmark                           Mode  Cnt       Score      Error  Units
BenchmarkMyLinkedList.getBenchmark  avgt    5  127435.518 ± 2452.389  ns/op
```

#### Strengths

The linked list has constant time complexity (O(1)) for adding elements at the beginning or the end, which can be advantageous in certain use cases.
It uses dynamic memory allocation, which means they can grow and shrink during runtime, providing flexibility in managing memory (as opposed to an array like int[]).
It inherently support easy insertion and deletion of elements in both ends, since it only involves updating pointers.

#### Weaknesses

Accessing elements in a linked list has a linear time complexity (O(n)), making it inefficient for random access or searching for elements.
The benchmark results show that adding elements to the linked list led to an OutOfMemoryError, indicating that it may not be suitable for large-scale applications or environments with limited memory resources.
Linked lists have higher data overhead due to the storage of pointers, making them less memory-efficient compared to arrays or (native) hash maps.

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

```
Result "sh.sinux.musicmanager.BenchmarkMyHashMap.getBenchmark":
  64790.654 ±(99.9%) 133.347 ns/op [Average]
  (min, avg, max) = (64760.201, 64790.654, 64837.257), stdev = 34.630
  CI (99.9%): [64657.307, 64924.001] (assumes normal distribution)

```

```
Result "sh.sinux.musicmanager.BenchmarkMyHashMap.putBenchmark":
  190903.060 ±(99.9%) 648.845 ns/op [Average]
  (min, avg, max) = (190710.250, 190903.060, 191056.054), stdev = 168.503
  CI (99.9%): [190254.215, 191551.905] (assumes normal distribution)
```

```
Benchmark                        Mode  Cnt       Score     Error  Units
BenchmarkMyHashMap.getBenchmark  avgt    5   64790.654 ± 133.347  ns/op
BenchmarkMyHashMap.putBenchmark  avgt    5  190903.060 ± 648.845  ns/op
```

#### Strengths

Hash maps offer constant average time complexity (O(1)) for key-based operations like put and get, making them efficient for random access and search operations.
The benchmark results indicate that hash maps have significantly better performance for get operations compared to linked lists. I can't compare with puting data, as the benchmark failed for the linked list.
Hash maps can handle dynamic resizing, allowing for efficient memory usage and performance.

#### Weaknesses

The worst-case time complexity for hash map operations can be linear (O(n)) if there are many hash collisions, which could lead to performance degradation.
A hash map relies on the data's hash function to distribute keys evenly, so the responsibility to have good performance is largely dependent on the quality of the hash function used and the proper management of the underlying data structure.
The data overhead for hash maps can be higher than arrays, as they need to store key-value pairs and maintain the internal data structure.

### Data structure recommendation

For the purpose of this app, we need a efficient data structure which should 
- support retrieving a song from it's Id (track number)
- search the structure with song criteria
- adding/removing a song

For this job I'd use a hash map for storing information with the ID as the key (because it's fast to retreive), along with binary trees for the most common criterias of search.
Indeed, a library of music is more likely to be read than written (we more often read music than edit a library), so it should be optimise for searching. A binary tree could speed-up the time to look for a song, while keeping the advantage of constant retrieval time for a song using a hashmap (once you know the ID).

For example, once you have set a playlist by searching the songs you want, you just store a list of IDs of songs for this playlist.
Retrieving your song is then donne in a constant time, instead of looking up through a whole linked list (for example).

### Playlist structures

#### Storing songs in a playlist

For the playlist itself, we had the choice between a stack and a queue to store the songs. Both are inefficient in this context, as we don't consume data, we read it several times.

So we choose to use and implement a queue of integers.
This way we're not storing the song itself, that avoid duplication and we can check if song still exists when asking it to the library.

To access an integer in the queue from a given position, we access it in Ө(n), meaning that even if position is found before, we still need to continue looping over the queue to reconstruct it in its original state. Note that we construct a temporary queue in which we enqueue all that we dequeued from the original queue, then just replace the original one and return the required value.

With a stack, it could be worst: it's O(2*n) (I know it's an incorrect notation, but that's to demonstrate) and Ω(1). Indeed if we want to access a value on top of the stack, it's done in a constant time. But if we want the last one, we have to pop all value and stack them in another stack, get the final value and finaly stack back all the values. We can't just replace the original stack with the temporary one (like we did with the queue), as the order is not the same.

#### Storing playlists

It was simpler in the app to interact with playlist ID rather than the name (writing the playlist name each time would be too long).
So we choosed to use our MyHashMap to store the playlists, so we could access a playlist with just its ID.
We always know the ID of a playlist, and we don't search for a playlist's name or song, so a list or a tree would be too complicated for nothing.
