package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayIterator<T> implements Iterator<T> {
    private T[] array;
    private int index = 0;

    public SimpleArrayIterator(T[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        while (index != array.length && array[index] == null) {
            index++;
        }
        return index < array.length;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array[index++];
    }
}
