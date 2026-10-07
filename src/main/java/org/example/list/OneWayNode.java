package org.example.list;

import java.util.Objects;

public class OneWayNode<T> {

    private final T element;
    private OneWayNode<T> next;

    public OneWayNode(T element) {
        this.element = element;
    }

    @Override
    public String toString() {
        if (Objects.isNull(this.next)) {
            return this.element.toString();
        }
        return "%s, %s".formatted(this.element, this.next);
    }

    public void addNext(OneWayNode<T> next) {
        this.next = next;
    }

    public OneWayNode<T> getNext() {
        return this.next;
    }

    public T getElement() {
        return this.element;
    }
}
