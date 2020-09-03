package ru.job4j.generic;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private int capacity;
    private int index = 0;
    private T[] array;

    public SimpleArray(int capacity) {
        this.capacity = capacity;
        array = (T[])new Object[capacity];
    }

    public void add(T model) {
        if (index < capacity) {
            array[index] = model;
            index++;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, capacity);
        array[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, capacity);
        System.arraycopy(array, index + 1, array, index, array.length - index - 1);
        array[array.length - 1] = null;
    }

    public T get(int index) {
        Objects.checkIndex(index, capacity);
        return array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator<>(array);
    }

}
