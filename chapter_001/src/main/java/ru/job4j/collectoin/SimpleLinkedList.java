package ru.job4j.collectoin;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;
    private int modeCount = 0;

    public void add(T value) {
        Node<T> node = new Node<>(value, null, null);
        if (head == null) {
            head = node;
        } else if (tail == null) {
            head.next = node;
            node.previous = head;
            tail = node;
        } else {
            node.previous = tail;
            tail.next = node;
            tail = node;
        }
        size++;
        modeCount++;
    }

    public T get(int index) {
        if (Objects.checkIndex(index, size) != index) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> result = head;
        while (index > 0) {
            result = result.next;
            index--;
        }
        return result.value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int expectedModCount = modeCount;
            private int index = 0;
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modeCount) {
                    throw new ConcurrentModificationException();
                }
                T lastValue = current.value;
                current = current.next;
                index++;
                return lastValue;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;
        Node<T> previous;

        public Node(T value, Node<T> next, Node<T> previous) {
            this.value = value;
            this.next = next;
            this.previous = previous;
        }
    }

}
