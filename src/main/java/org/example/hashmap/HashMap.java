package org.example.hashmap;

public class HashMap<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private int capacity;
    private float loadFactor;
    private int size;
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
        int hash = hash(key);
        int index = getIndex(hash);
        Node<K, V> current = table[index];

        while (current != null) {
            if (current.hash == hash && equals(current.key, key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        Node<K, V> newNode = new Node<>(hash, key, value, table[index]);
        table[index] = newNode;
        size++;
        if (size > table.length * loadFactor) {
            resize();
        }
    }

    private int getIndex(int hash) {

        return hash & (table.length - 1);
    }

    private int hash(K key) {

        if (key == null) {
            return 0;
        }
        int hash = key.hashCode();
        return hash ^ (hash >>> 16);
    }

    private boolean equals(K key1, K key2) {

        if (key1 == key2) {
            return true;
        }

        if (key1 == null || key2 == null) {
            return false;
        }

        return key1.equals(key2);
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

    public int size() {
        return size;
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
