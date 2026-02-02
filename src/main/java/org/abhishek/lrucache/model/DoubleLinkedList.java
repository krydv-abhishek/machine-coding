package org.abhishek.lrucache.model;

public class DoubleLinkedList<K, V> {

    private Node<K, V> head;
    private Node<K, V> tail;

    public DoubleLinkedList() {
        head = new Node<>(null, null);
        tail = new Node<>(null, null);
        head.right = tail;
        tail.left = head;
    }

    public void addNode(Node<K, V> node) {
        node.right = head.right;
        node.left = head;
        head.right.left = node;
        head.right = node;
    }

    public void removeNode(Node<K, V> node) {
        if (head.right != tail) {
            node.left.right = node.right;
            node.right.left = node.left;
        } else {
            System.out.println("List is empty");
        }
    }

    public void moveToHead(Node<K, V> node) {
        removeNode(node);
        addNode(node);
    }

    public Node<K, V> removeTail() {
        if (head.right != tail) {
            Node<K, V> node = tail.left;
            removeNode(node);
            return node;
        }else {
            System.out.println("List is empty");
            return null;
        }
    }
}
