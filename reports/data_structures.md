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
#### Weaknesses

### Data structure recommendation

### Playlist structures
