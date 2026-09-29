package org.example.queue;

import java.util.Optional;

public class PriorityQueue<T extends Comparable<T>> {

    Object[] heap;
    int size = 0;

    public PriorityQueue(int initialCapacity) {
        this.heap = new Object[initialCapacity];
    }

    private PriorityQueue(Object[] heap, int size) {
        this.size = size;
        this.heap = new Object[size];
        System.arraycopy(heap, 0, this.heap, 0, size);
    }

    public PriorityQueue<T> copy() {
        return new PriorityQueue<>(this.heap, this.size);
    }

    public void add(T element)   {
        if (element == null) {
            return;
        }

        ensureCapacity();
        heap[size] = element;

        siftUp(size++);

    }

    public Optional<T> poll() {
        if (isEmpty()) {
            return Optional.empty();
        }

        T result = elementAt(0);
        heap[0] = heap[size - 1];
        heap[size - 1] = null;

        size--;

        if (size > 0) {
            siftDown(0);
        }

        return Optional.ofNullable(result);
    }

    public Optional<T> peek() {
        if (isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(elementAt(0));
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {

        if (size < heap.length) {
            return;
        }

        Object[] newHeap = new Object[heap.length * 2];
        System.arraycopy(heap, 0, newHeap, 0, heap.length);
        heap = newHeap;
    }

    private void siftUp(int index) {
        while (index > 0) {

            int parent = (index - 1) / 2;

            T current = elementAt(index);
            T parentElement = elementAt(parent);
            if (parentElement.compareTo(current) <= 0) {
                break;
            }
            swap(index, parent);
            index = parent;
        }
    }

    private void siftDown(int index) {

        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < size && elementAt(left).compareTo(elementAt(smallest)) < 0) {
                smallest = left;
            }

            if (right < size && elementAt(right).compareTo(elementAt(smallest)) < 0) {
                smallest = right;
            }

            if (smallest == index) {
                break;
            }

            swap(index, smallest);

            index = smallest;
        }
    }

    private T elementAt(int index) {
        return (T) heap[index];
    }

    private void swap(int i, int j) {
        Object temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    @Override
    public String toString() {
        var copy = copy();
        StringBuilder s = new StringBuilder();
        while (!copy.isEmpty()) {
            copy.poll().ifPresent(t -> s.append("%s | ".formatted(t)));
        }
        return s.toString();
    }
}
