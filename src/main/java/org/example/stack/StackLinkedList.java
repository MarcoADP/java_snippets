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
        return this.elements.get(this.size()-1);
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

    // TODO Iterator
    public int search(T element) {
        var node = this.elements.getNode(this.size()-1);
        var position = 1;
        while (Objects.nonNull(node)) {
            if (Objects.equals(node.getElement(), element)) {
                return position;
            }
            position++;
            node = node.getPrevious();
        }
        return -1;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder("[");
        var node = this.elements.getNode(this.size()-1);
        while (Objects.nonNull(node)) {
            builder.append(node.getElement());
            if (Objects.nonNull(node.getPrevious())) {
                builder.append(", ");
            }
            node = node.getPrevious();
        }
        builder.append("]");
        return builder.toString();
    }
}
