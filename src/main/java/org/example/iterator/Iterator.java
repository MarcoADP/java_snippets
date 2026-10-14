package org.example.iterator;

public interface Iterator<T> {

    boolean hasNext();

    T next();

    default void remove() {
        throw new UnsupportedOperationException("No remove support");
    }

}
