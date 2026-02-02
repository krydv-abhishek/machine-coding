package org.abhishek.lrucache.service;

import java.util.LinkedHashMap;
import java.util.Map;

/*
LinkedHashMap internally already uses a hash table and a doubly linked list and supports access-order iteration,
so it’s a clean way to implement LRU. I’d use it unless I need custom eviction logic, TTL, high concurrency,
or advanced policies.
 */
public class LRUCacheWithLHMap<K, V> extends LinkedHashMap<K, V> {

    private final int capacity;

    public LRUCacheWithLHMap(int capacity) {
        super(capacity, 0.75f, true); // accessOrder = true
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}
