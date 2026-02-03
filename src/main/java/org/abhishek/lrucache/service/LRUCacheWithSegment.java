package org.abhishek.lrucache.service;

import org.abhishek.lrucache.model.Segment;
import org.abhishek.lrucache.repository.LRURepositoryWithSegment;

import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

public class LRUCacheWithSegment<K, V> {

    private final LRURepositoryWithSegment<K, V> lruRepository;
    private final int capacity;
    private final ReentrantLock lock = new ReentrantLock();


    public LRUCacheWithSegment(int capacity, int segmentSize) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.capacity = capacity / segmentSize;
        this.lruRepository = new LRURepositoryWithSegment<>(capacity / segmentSize);
    }

    public void addKey(K key, V value) {
        Segment<K, V> segment = lruRepository.getSegment(key);
        segment.getLock().lock();
        try {
            Objects.requireNonNull(key, "Key should not be null");
            Objects.requireNonNull(value, "Value should not be null");
            if (lruRepository.getSize(segment) >= capacity) {
                lruRepository.removeTail(segment);
            }
            lruRepository.addKey(segment, key, value);
            System.out.println("key added" + key);
        } finally {
            segment.getLock().unlock();
        }
    }

    public void addKey(K key, V value, long ttlInSeconds) {
        Segment<K, V> segment = lruRepository.getSegment(key);
        segment.getLock().lock();
        try {
            Objects.requireNonNull(key, "Key should not be null");
            Objects.requireNonNull(value, "Value should not be null");
            if (lruRepository.getSize(segment) >= capacity) {
                lruRepository.removeTail(segment);
            }
            lruRepository.addKey(segment, key, value, ttlInSeconds);
        } finally {
            segment.getLock().unlock();
        }
    }

    public V getKey(K key) {
        Segment<K, V> segment = lruRepository.getSegment(key);
        segment.getLock().lock();
        try {
            Objects.requireNonNull(key, "Key should not be null");
            System.out.println("key fetched" + key);
            return (V) lruRepository.getKey(segment, key);
        } finally {
            segment.getLock().unlock();
        }
    }
}
