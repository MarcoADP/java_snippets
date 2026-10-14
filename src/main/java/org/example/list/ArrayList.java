package org.example.list;

import org.example.iterator.Iterable;
import org.example.iterator.Iterator;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements Iterable<T> {

    public static final int DEFAULT_CAPACITY = 10;

    Object[] elements;
    int capacity;
    int size = 0;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be bigger than 0");
        }
        this.elements = new Object[capacity];
        this.capacity = capacity;
    }

    public void add(T newElement) {
        if (size == capacity) {
            resize();
        }
        this.elements[size] = newElement;
        size++;
    }

    private void resize() {
        var newCapacity = 2 * capacity;
        var newArray = new Object[newCapacity];

        for (var i = 0; i < size; i++) {
            newArray[i] = this.elements[i];
        }

        this.capacity = newCapacity;
        this.elements = newArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) this.elements[index];
    }

    public T getFirst() {
        if (isEmpty()) {
            return null;
        }
        return get(0);
    }

    public T getLast() {
        if (isEmpty()) {
            return null;
        }
        return get(size - 1);
    }

    public void removeAt(int index) {
        checkIndex(index);

        for (var i = index; i < size-1; i++ ) {
            this.elements[i] = this.elements[i+1];
        }

        this.size--;
        this.elements[this.size] = null;

    }

    public void removeFirst() {
        this.removeAt(0);
    }

    public void removeLast() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Index: " + -1 + ", Size: " + size);
        }
        this.elements[size - 1] = null;
        this.size--;
    }

    public void remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], element)) {
                removeAt(i);
                return;
            }
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(T element) {
        if (isEmpty()) {
            return false;
        }
        for (var i = 0; i < size; i++) {
            if (Objects.equals(elements[i], element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder("[");
        var iterator = this.iterator();
        while (iterator.hasNext()) {
            builder.append("%s".formatted(iterator.next()));
            if (iterator.hasNext()) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private int cursor = 0;
            private int lastReturned = -1;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                lastReturned = cursor;
                var element = get(cursor);
                cursor++;
                return element;
            }

            @Override
            public void remove() {
                if (lastReturned == -1) {
                    throw new IllegalStateException("Call next() before remove()");
                }

                removeAt(lastReturned);

                cursor = lastReturned;
                lastReturned = -1;
            }
        };
    }
}
