package org.abhishek.lrucache.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Segment<K,V> {
    private final ReentrantLock lock = new ReentrantLock();
    private final Map<K, Node<K, V>> map = new HashMap<>();
    private final DoubleLinkedList<K, V> doubleLinkedList = new DoubleLinkedList();

    public ReentrantLock getLock() {
        return lock;
    }

    public Map<K, Node<K, V>> getMap() {
        return map;
    }

    public DoubleLinkedList<K, V> getDoubleLinkedList() {
        return doubleLinkedList;
    }
}
