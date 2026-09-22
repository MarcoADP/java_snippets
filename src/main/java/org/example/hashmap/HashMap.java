package org.example.hashmap;

import java.util.Optional;

public class HashMap<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private int capacity;
    private float loadFactor;
    private Node<K, V>[] table;

    public HashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashMap(int capacity, float loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.table = (Node<K, V>[]) new Node[capacity];
    }

    public void put(K key, V value) {
        var hash = hash(key);
        var index = getIndex(hash);

        var node = this.table[index];
        while (node != null) {
            if (node.key == key) {
                node.value = value;
                return;
            }
            node = node.next;
        }
        var newNode = new Node<>(hash, key, value, table[index]);
        this.table[index] = newNode;

        if (this.table.length > (capacity * loadFactor)) {
            resize();
        }

    }

    private int getIndex(int hash) {
        return hash & (table.length - 1);
    }

    private int hash(K key) {
        return key.hashCode();
    }

    private void resize() {

        Node<K, V>[] oldTable = table;
        Node<K, V>[] newTable = new Node[oldTable.length * 2];
        table = newTable;

        for (Node<K, V> node : oldTable) {
            while (node != null) {
                Node<K, V> next = node.next;
                int index = getIndex(node.hash);
                node.next = newTable[index];
                newTable[index] = node;
                node = next;
            }
        }
    }

    public V get(K key) {
        var hash = hash(key);
        var index = getIndex(hash);
        var node = this.table[index];
        while (node != null) {
            if (node.key == key) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public Optional<V> getOptional(K key) {
        return Optional.ofNullable(get(key));
    }

    public V getOptionalOrDefault(K key, V defaultValue) {
        return getOptional(key).orElse(defaultValue);
    }

    @Override
    public String toString() {
        var str = "";
        for (var node : table) {
            if (node != null) {
                str += node + " hash: " + hash(node.key) + "\n";
            }
        }
        return str;
    }
}
