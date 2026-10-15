package org.example.stack;

import org.example.list.DoublyLinkedList;

import java.util.NoSuchElementException;
import java.util.Objects;

public class StackLinkedList<T> {

    private final DoublyLinkedList<T> elements;

    public StackLinkedList() {
        this.elements = new DoublyLinkedList<>();
    }

    public void push(T element) {
        this.elements.addLast(element);
    }

    public T pop() {
        return this.elements.removeLast();
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.elements.getLast();
    }

    public boolean isEmpty() {
        return this.elements.isEmpty();
    }

    public int size() {
        return this.elements.size();
    }

    public void clear() {
        this.elements.clear();
    }

    public int search(T element) {
        var iterator = this.elements.descendingIterator();
        var position = 1;
        while (iterator.hasNext()) {
            if (Objects.equals(iterator.next(), element)) {
                return position;
            }
            position++;
        }
        return -1;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder("[");
        var iterator = this.elements.descendingIterator();
        while (iterator.hasNext()) {
            builder.append(iterator.next());
            if (iterator.hasNext()) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
