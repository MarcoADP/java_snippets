package org.example.stack;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class StackArray<T> {

    public static final int CAPACITY = 20;
    private Object[] elements;
    private int size = 0;
    private int capacity;

    public StackArray() {
        this(CAPACITY);
    }

    public StackArray(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero");
        }
        this.elements = new Object[capacity];
        this.capacity = capacity;
    }

    private void resize() {
        var newCapacity = 2 * capacity;
        this.capacity = newCapacity;
        this.elements = Arrays.copyOf(elements, newCapacity);
    }

    public void push(T element) {
        if (this.size == this.capacity) {
            resize();
        }

        elements[size] = element;

        size++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        var position = this.size - 1;
        var element = this.elements[position];

        this.elements[position] = null;
        size--;

        return (T) element;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        var position = this.size - 1;
        return (T) elements[position];
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void clear() {
        while(!isEmpty()) {
            this.pop();
        }
    }

    public int search(T element) {
        for (var i = this.size - 1; i >= 0; i--) {
            if (Objects.equals(this.elements[i], element)) {
                return this.size - i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder("[");
        for (var i = this.size-1; i >= 0; i--) {
            builder.append(this.elements[i]);
            if (i > 0) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
