package org.example.hashmap;

import org.example.lock.ReadWriteLock;

import java.util.Optional;

public class ConcurrentHashMap<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private int capacity;
    private float loadFactor;
    private Node<K, V>[] table;
    private ReadWriteLock lock;
    private int size = 0;

    public ConcurrentHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public ConcurrentHashMap(int capacity, float loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.table = (Node<K, V>[]) new Node[capacity];
        this.lock = new ReadWriteLock();
    }

    public void put(K key, V value) {
        var hash = hash(key);
        var index = getIndex(hash);

        try {

            lock.addWriteLock(value.toString());

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
            this.size++;

            System.out.printf("%s -- %s%n", this.size, capacity * loadFactor);
            if (this.size > (capacity * loadFactor)) {
                System.out.println("Resizing....");
                resize();
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            lock.unlockWriteLock();
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
        this.capacity = oldTable.length * 2;
        Node<K, V>[] newTable = new Node[capacity];
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
        try {
            lock.addReadLock(key.toString());
            var hash = hash(key);
            var index = getIndex(hash);
            var node = this.table[index];
            while (node != null) {
                if (node.key == key) {
                    return node.value;
                }
                node = node.next;
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            lock.unlockRead();
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
