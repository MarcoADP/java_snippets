package org.example.list;

public class TwoWayNode<T> {

    private final T element;
    private TwoWayNode<T> previous;
    private TwoWayNode<T> next;

    public TwoWayNode(T element) {
        this.element = element;
    }

    @Override
    public String toString() {
        return this.element.toString();
    }

    public void addPrevious(TwoWayNode<T> previous) {
        this.previous = previous;
    }

    public void addNext(TwoWayNode<T> next) {
        this.next = next;
    }

    public TwoWayNode<T> getNext() {
        return this.next;
    }

    public TwoWayNode<T> getPrevious() {
        return this.previous;
    }

    public T getElement() {
        return this.element;
    }
}
