# LRU Cache

## Quick revision

- **When does `updateData` evict?** When the incoming `key` is absent and the cache is full. An existing key can be updated in place. A successful write refreshes the key's recency. [Cache.java](core/Cache.java)
- **How does LRU pick `evictKey`?** A hash map finds each key's list node. The list puts the most recently used key at the tail and picks the least recently used key from the head. [LRUEvictionPolicy.java](evictionpolicy/LRUEvictionPolicy.java)
- **What do the indexes mean?** `currentIndex` is the worker already running the update. `evictedIndex` is the worker assigned to `evictKey`. Each executor has one thread, and the hash of a key determines its worker. [KeyBasedExecutors.java](executors/KeyBasedExecutors.java)
- **Why remove `evictKey`?** `key` is the incoming entry and is still absent. `cacheStorage.remove(evictKey)` removes the existing entry and frees space. [Cache.java](core/Cache.java)
- **What does write-through do?** It writes the value to both cache storage and DB storage and waits for both writes to finish. [WriteThroughCache.java](writepolicy/WriteThroughCache.java)

### The executor decision in `updateData`

With two workers, suppose worker 0 is inserting key `1` into a full cache.

| LRU picks | Decision | Why |
| --- | --- | --- |
| Key `3`, also on worker 0 | Remove `3` directly inside the update job. | Queueing the removal on worker 0 and calling `join()` would deadlock. Worker 0 would wait inside the update, and the queued removal could only start after the update finishes. |
| Key `2`, on worker 1 | Queue the removal on worker 1 and call `join()`. | The removal runs in the same queue as other jobs for key `2`. The update waits for the space to be freed before inserting key `1`. |

## Class diagram

```mermaid
classDiagram
    direction TB

    class Main {
        +main(String[] args) void$
    }

    class Cache {
        +accessData(K key) CompletableFuture~V~
        +updateData(K key, V value) CompletableFuture~Void~
        +shutdown() void
    }

    class KeyBasedExecutors {
        +submitTask(Object key, Supplier~T~ task) CompletableFuture~T~
        +getExecutorIndexForKey(Object key) int
        +shutdown() void
    }

    class ExecutorService {
        <<interface>>
    }

    class EvictionAlgorithm {
        <<interface>>
        +keyAccessed(K key) void
        +evictKey() K
    }

    class LRUEvictionPolicy {
        +keyAccessed(K key) void
        +evictKey() K
    }

    class DoublyLinkedList {
        +getHead() DoublyLinkedListNode~K~
        +addNodeToTail(DoublyLinkedListNode~K~ node) void
        +detachNode(DoublyLinkedListNode~K~ node) void
        +removeHead() void
    }

    class DoublyLinkedListNode {
        -K value
        -DoublyLinkedListNode~K~ prev
        -DoublyLinkedListNode~K~ next
    }

    class CacheStorage {
        <<interface>>
        +put(K key, V value) void
        +get(K key) V
        +exist(K key) boolean
        +remove(K key) void
        +size() int
        +capacity() int
    }

    class InMemoryCacheStorage {
        -int capacity
        +InMemoryCacheStorage(int capacity)
    }

    class DBStorage {
        <<interface>>
        +write(K key, V value) void
        +read(K key) V
        +delete(K key) void
    }

    class SimpleDBStorage

    class WritePolicy {
        <<interface>>
        +write(K key, V value, CacheStorage cacheStorage, DBStorage dbStorage) void
    }

    class WriteThroughCache

    Main ..> Cache : creates and calls

    Cache *-- KeyBasedExecutors : owns
    KeyBasedExecutors *-- "*" ExecutorService : single-thread workers

    Cache --> EvictionAlgorithm : tracks usage and selects a key to evict
    Cache --> CacheStorage : reads and removes entries
    Cache --> DBStorage : passes to write policy
    Cache --> WritePolicy : delegates writes

    LRUEvictionPolicy ..|> EvictionAlgorithm
    InMemoryCacheStorage ..|> CacheStorage
    SimpleDBStorage ..|> DBStorage
    WriteThroughCache ..|> WritePolicy

    LRUEvictionPolicy *-- DoublyLinkedList : tracks recency
    LRUEvictionPolicy --> "0..*" DoublyLinkedListNode : indexes by key
    DoublyLinkedList --> "0..*" DoublyLinkedListNode : links nodes

    WriteThroughCache ..> CacheStorage : writes to cache
    WriteThroughCache ..> DBStorage : writes to database

    note for Cache "When inserting into a full cache:\nSame executor: remove the old key directly.\nDifferent executors: queue removal and wait."
```
