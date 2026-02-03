package org.abhishek.lrucache.repository;

import org.abhishek.lrucache.model.Node;
import org.abhishek.lrucache.model.Segment;

public class LRURepositoryWithSegment<K, V> {

    private final Segment<K, V>[] segments;

    public LRURepositoryWithSegment(int segmentCount) {
        segments = new Segment[segmentCount];
        for (int i = 0; i < segmentCount; i++) {
            segments[i] = new Segment<>();
        }
    }

    public Segment<K, V> getSegment(K key) {
        int hash = key.hashCode();
        return segments[(hash & 0x7fffffff) % segments.length];
    }

    public void addKey(Segment<K, V> segment, K key, V value) {
        Node<K, V> node = segment.getMap().get(key);
        if (node != null) {
            node.setValue(value);
            segment.getDoubleLinkedList().moveToHead(node);
            return;
        }
        Node<K, V> newNode = new Node<>(key, value);
        segment.getMap().put(key, newNode);
        segment.getDoubleLinkedList().addNode(newNode);
    }

    public void addKey(Segment<K, V> segment, K key, V value, long ttlInSeconds) {
        Node<K, V> node = segment.getMap().get(key);
        if (node != null) {
            node.setValue(value);
            segment.getDoubleLinkedList().moveToHead(node);
            return;
        }
        Node<K, V> newNode = new Node<>(key, value, ttlInSeconds);
        segment.getMap().put(key, newNode);
        segment.getDoubleLinkedList().addNode(newNode);
    }

    public V getKey(Segment<K, V> segment, K key) {
        Node<K, V> node = segment.getMap().get(key);
        if (node == null) {
            return null;
        }
        if (node.isExpired()) {
            removeNode(segment, node);
            return null;
        }
        segment.getDoubleLinkedList().moveToHead(node);
        return node.getValue();
    }

    public void removeTail(Segment<K, V> segment) {
        Node<K, V> node = segment.getDoubleLinkedList().removeTail();
        segment.getMap().remove(node.getKey());
    }

    public void removeNode(Segment<K, V> segment, Node<K, V> node) {
        segment.getDoubleLinkedList().removeNode(node);
        segment.getMap().remove(node.getKey());
    }

    public int getSize(Segment<K, V> segment) {
        return segment.getMap().size();
    }
}
