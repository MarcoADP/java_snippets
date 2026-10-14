package org.example.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class DoublyLinkedList<T> {

    private TwoWayNode<T> first;
    private TwoWayNode<T> last;
    private int size = 0;

    public DoublyLinkedList() { }

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
        var node = new TwoWayNode<>(element);
        if (isEmpty()) {
            this.first = node;
        } else {
            var l = this.last;
            l.addNext(node);
            node.addPrevious(l);
        }
        this.last = node;
        size++;
    }

    public void addFirst(T element) {
        var node = new TwoWayNode<>(element);
        node.addNext(this.first);
        if (isEmpty()) {
            this.last = node;
        } else {
            var f = this.first;
            f.addPrevious(node);
        }
        this.first = node;
        size++;
    }

    public void add(int index, T element) {
        if (index < 0 || index > size) {
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

        var newNode = new TwoWayNode<>(element);
        var node = getNode(index);
        var previous = node.getPrevious();

        previous.addNext(newNode);
        newNode.addPrevious(previous);

        newNode.addNext(node);
        node.addPrevious(newNode);
        size++;
    }

    public TwoWayNode<T> getNode(int index) {
        int half = size / 2;
        if (index < half) {
            return getNodeFromBeginning(index);
        } else {
            return getNodeFromEnding(index);
        }
    }

    private TwoWayNode<T> getNodeFromBeginning(int index) {
        var node = this.first;
        for (var i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node;
    }

    private TwoWayNode<T> getNodeFromEnding(int index) {
        var node = this.last;
        for (var i = size - 1; i > index; i--) {
            node = node.getPrevious();
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
        } else {
            this.first.addPrevious(null);
        }
        f.addNext(null);
        f.addPrevious(null);
        size--;
        return f.getElement();
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        var l = this.last;
        var previous = this.last.getPrevious();
        if (Objects.isNull(previous)) {
            return removeFirst();
        }
        previous.addNext(null);
        this.last = previous;

        l.addPrevious(null);
        l.addNext(null);

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
        var node = getNode(index);
        var previous = node.getPrevious();
        var next = node.getNext();

        previous.addNext(next);
        next.addPrevious(previous);

        node.addPrevious(null);
        node.addNext(null);

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
