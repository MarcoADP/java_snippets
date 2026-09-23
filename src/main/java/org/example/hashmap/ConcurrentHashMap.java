package org.example.hashmap;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class ConcurrentHashMap<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private int capacity;
    private final float loadFactor;
    private volatile AtomicReferenceArray<VolatileNode<K, V>> table;
    private int size = 0;

    public ConcurrentHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public ConcurrentHashMap(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    public ConcurrentHashMap(int capacity, float loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.table = new AtomicReferenceArray<>(capacity);
    }

    public void put(K key, V value) {
        var hash = hash(key);
        var index = getIndex(hash);

        while (true) {

            AtomicReferenceArray<VolatileNode<K, V>> currentTable = table;
            var currentNode = currentTable.get(index);

            if (currentNode == null) {
                var newNode = new VolatileNode<>(hash, key, value);
                if (currentTable.compareAndSet(index, null, newNode)) {

                    testResize(currentTable);
                    return;
                }

                continue;
            }

            synchronized (currentNode) {
                var node = currentTable.get(index);

                while (true) {
                    if (node.hash == hash && node.key.equals(key)) {
                        node.value = value;
                        return;
                    }

                    if (node.next == null) {
                        break;
                    }

                    node = node.next;
                }

                node.next = new VolatileNode<>(hash, key, value);
                testResize(currentTable);

                return;

            }

        }
    }

    private void testResize(AtomicReferenceArray<VolatileNode<K, V>> currentTable) {
        size++;

        if (size > currentTable.length() * this.loadFactor) {
            resize();
        }
    }

    private int getIndex(int hash) {
        return hash & (table.length() - 1);
    }

    private int hash(K key) {
        var hash = key.hashCode();
        return hash ^ (hash >>> 16);
    }

    public V get(K key) {
        var hash = hash(key);
        var index = getIndex(hash);
        var node = this.table.get(index);

        while (node != null) {
            if (node.hash == hash && node.key.equals(key)) {
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

    private void resize() {
        int oldCapacity = capacity;
        int newCapacity = this.capacity * 2;
        System.out.printf("resizing... %s to %s -- Table: %s%n", oldCapacity, newCapacity, table.length());

        AtomicReferenceArray<VolatileNode<K, V>> newTable = new AtomicReferenceArray<>(newCapacity);

        for (int i = 0; i < oldCapacity; i++) {
            var node = table.get(i);

            while (node != null) {
                var next = node.next;
                int newIndex = node.hash & (newCapacity - 1);
                node.next = newTable.get(newIndex);
                newTable.set(newIndex, node);
                node = next;
            }
        }

        this.capacity = newCapacity;
        this.table = newTable;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < table.length(); i++) {
            var node = table.get(i);
            if (node != null) {
                str.append(node).append(" hash: ").append(hash(node.key)).append("\n");
            }
        }
        return str.toString();
    }
}
