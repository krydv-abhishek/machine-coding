package org.abhishek.lrucache.model;

public class Node<K, V> {

    private K key;
    private V value;
    private Long expiryTime;
    Node left;
    Node right;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public Node(K key, V value, long ttlInSeconds) {
        this.key = key;
        this.value = value;
        this.left = null;
        this.right = null;
        this.expiryTime = System.currentTimeMillis() + ttlInSeconds * 1000;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public boolean isExpired() {
        if (this.expiryTime != null) {
            return System.currentTimeMillis() > this.expiryTime;
        }
        return false;
    }
}
