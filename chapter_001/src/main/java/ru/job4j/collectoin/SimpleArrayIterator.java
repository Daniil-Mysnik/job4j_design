package ru.job4j.collectoin;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayIterator<T> implements Iterator<T> {
    private int changes;
    private T[] array;
    private int index = 0;

    public SimpleArrayIterator(int changes, T[] array) {
        this.changes = changes;
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return null;
    }
}
