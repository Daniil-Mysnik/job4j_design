package ru.job4j.collectoin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private int capacity = 10;
    private int size = 0;
    private int changes = 0;
    private double loadFactor = 0.75;
    private T[] array = (T[])new Object[capacity];
    private int index = 0;

    public T get(int index) {
        Objects.checkIndex(index, capacity);
        List list = new ArrayList();
        return array[index];
    }

    public void add(T model) {
        if (size == (int) (capacity * loadFactor)) {
            capacity *= 2;
            T[] tmp = (T[])new Object[capacity];
            System.arraycopy(array, 0, tmp, 0, size - 1);
            array = tmp;
        }
        array[index++] = model;
        size++;
    }


    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator<T>(changes, array);
    }
}
