package org.abhishek.lrucache.service;

import org.abhishek.lrucache.repository.LRURepository;

import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

public class LRUCache<K, V> {

    private final LRURepository<K, V> lruRepository;
    private final int capacity;
    private final ReentrantLock lock = new ReentrantLock();


    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.capacity = capacity;
        this.lruRepository = new LRURepository<>();
    }

    public void addKey(K key, V value) {
        lock.lock();
        try {
            Objects.requireNonNull(key, "Key should not be null");
            Objects.requireNonNull(value, "Value should not be null");
            if (lruRepository.getSize() >= capacity) {
                lruRepository.removeTail();
            }
            lruRepository.addKey(key, value);
        } finally {
            lock.unlock();
        }
    }

    public V getKey(K key) {
        lock.lock();
        try {
            Objects.requireNonNull(key, "Key should not be null");
            return (V) lruRepository.getKey(key);
        } finally {
            lock.unlock();
        }
    }
}
