package org.example.hashmap;


public class VolatileNode<K, V> {
    final int hash;
    final K key;
    volatile V value;
    volatile VolatileNode<K, V> next;

    VolatileNode(int hash, K key, V value, VolatileNode<K, V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    VolatileNode(int hash, K key, V value) {
        this(hash, key, value, null);
    }

    @Override
    public String toString() {
        return "%s => %s [%s]".formatted(key, value, next);
    }
}
