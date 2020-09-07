package ru.job4j.collectoin;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private int capacity = 10;
    private int modeCount = 0;
    private Object[] container;
    private int position = 0;

    public SimpleArray() {
        this.container = new Object[capacity];
    }

    public T get(int index) {
        if (Objects.checkIndex(index, position) != index) {
            throw new IndexOutOfBoundsException();
        }
        return (T) container[index];
    }

    public void add(T model) {
        if (position >= capacity) {
            capacity *= 2;
            container = Arrays.copyOf(container, capacity);
        }
        container[position++] = model;
        modeCount++;
    }

    public boolean contains(T element) {
        for (int i = 0; i < position; i++) {
            if (container[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleArray<?> that = (SimpleArray<?>) o;
        return capacity == that.capacity &&
                modeCount == that.modeCount &&
                position == that.position &&
                Arrays.equals(container, that.container);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(capacity, modeCount, position);
        result = 31 * result + Arrays.hashCode(container);
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int expectedModeCount = modeCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                return position > index;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModeCount != modeCount) {
                    throw new ConcurrentModificationException();
                }
                return (T) container[index++];
            }
        };
    }
}
