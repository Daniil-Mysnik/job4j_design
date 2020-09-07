package ru.job4j.collectoin;

import java.util.Iterator;

public class SimpleSet<T> implements Iterable<T> {
    private SimpleArray<T> simpleArray = new SimpleArray<>();

    public void add(T element) {
        if (!simpleArray.contains(element)) {
            simpleArray.add(element);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return simpleArray.iterator();
    }
}
