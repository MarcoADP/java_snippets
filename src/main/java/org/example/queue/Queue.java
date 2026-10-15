package org.example.queue;

import org.example.list.DoublyLinkedList;

import java.util.NoSuchElementException;

public class Queue<T> {

    private final DoublyLinkedList<T> elements;

    public Queue() {
        this.elements = new DoublyLinkedList<>();
    }

    public boolean isEmpty() {
        return this.elements.isEmpty();
    }

    public boolean add(T element) {
        this.elements.addLast(element);
        return true;
    }

    public T poll() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.elements.removeFirst();
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.elements.getFirst();
    }

    public T last() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.elements.getLast();
    }

    public int size() {
        return this.elements.size();
    }

    public void clear() {
        this.elements.clear();
    }

    @Override
    public String toString() {
        var builder = new StringBuilder("[");
        var iterator = this.elements.iterator();
        while(iterator.hasNext()) {
            builder.append(iterator.next());
            if (iterator.hasNext()) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
