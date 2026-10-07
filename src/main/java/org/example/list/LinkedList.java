package org.example.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList<T> {

    private OneWayNode<T> first;
    private OneWayNode<T> last;
    private int size = 0;

    public LinkedList() { }

    @Override
    public String toString() {
        var builder = new StringBuilder("[");
        var node = first;
        while (node != null) {
            builder.append(node.getElement());
            if (node.getNext() != null) {
                builder.append(", ");
            }
            node = node.getNext();
        }
        return builder.append("]").toString();
    }

    public void add(T element) {
        addLast(element);
    }

    public void addLast(T element) {
        var node = new OneWayNode<>(element);
        if (isEmpty()) {
            this.first = node;
        } else {
            this.last.addNext(node);
        }
        this.last = node;
        size++;
    }

    public void addFirst(T element) {
        var node = new OneWayNode<>(element);
        node.addNext(this.first);
        if (isEmpty()) {
            this.last = node;
        }
        this.first = node;
        size++;
    }

    public void add(int index, T element) {
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            addFirst(element);
            return;
        }

        if (index == size) {
            addLast(element);
            return;
        }

        var previous = getNode(index-1);
        var node = previous.getNext();
        var newNode = new OneWayNode<>(element);
        previous.addNext(newNode);
        newNode.addNext(node);
        size++;
    }

    private OneWayNode<T> getNode(int index) {
        var node = this.first;
        for (var i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node;
    }

    public T get(int index) {
        checkIndex(index);
        var node = getNode(index);
        return node.getElement();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean isEmpty() {
        return Objects.isNull(this.first);
    }

    public int size() {
        return size;
    }

    public T remove() {
        return this.removeFirst();
    }

    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        var f = this.first;
        this.first = f.getNext();
        if (Objects.isNull(this.first)) {
            this.last = null;
        }
        size--;
        return f.getElement();
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        var l = this.last;
        var indexPrevious = size - 2;
        if (indexPrevious < 0) {
            return removeFirst();
        }

        var previous = this.getNode(indexPrevious);
        previous.addNext(null);
        this.last = previous;
        size--;
        return l.getElement();
    }

    public T removeAt(int index) {
        checkIndex(index);
        if (index == 0) {
            return removeFirst();
        }
        if (index == size - 1) {
            return removeLast();
        }
        var previous = getNode(index - 1);
        var node = previous.getNext();
        previous.addNext(node.getNext());
        size--;
        return node.getElement();
    }

    public boolean contains(T element) {
        var node = this.first;
        while (Objects.nonNull(node)) {
            if (Objects.equals(node.getElement(),element)) {
                return true;
            }
            node = node.getNext();
        }
        return false;
    }

    public void clear() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }
}
