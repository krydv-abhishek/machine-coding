package org.abhishek.lrucache.model;

public class Node<K, V> {

    private K key;
    private V value;
    Node left;
    Node right;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.left = null;
        this.right = null;
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
}
