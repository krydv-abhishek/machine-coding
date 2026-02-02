package org.abhishek.lrucache.repository;

import org.abhishek.lrucache.model.DoubleLinkedList;
import org.abhishek.lrucache.model.Node;

import java.util.concurrent.ConcurrentHashMap;

public class LRURepository<K, V> {

    private final ConcurrentHashMap<K, Node<K, V>> map;
    private final DoubleLinkedList<K, V> doubleLinkedList;

    public LRURepository() {
        map = new ConcurrentHashMap<>();
        doubleLinkedList = new DoubleLinkedList<>();
    }

    public void addKey(K key, V value) {
        Node<K, V> node = map.get(key);
        if (node != null) {
            node.setValue(value);
            doubleLinkedList.moveToHead(node);
            return;
        }
        Node<K, V> newNode = new Node<>(key, value);
        map.put(key, newNode);
        doubleLinkedList.addNode(newNode);
    }

    public V getKey(K key) {
        Node<K, V> node = map.get(key);
        if(node==null) {
            return null;
        }
        doubleLinkedList.moveToHead(node);
        return node.getValue();
    }

    public void removeTail() {
        Node<K, V> node = doubleLinkedList.removeTail();
        map.remove(node.getKey());
    }

    public int getSize() {
        return map.size();
    }
}
