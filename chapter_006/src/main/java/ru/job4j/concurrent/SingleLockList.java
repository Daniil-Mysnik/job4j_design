package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.collectoin.SimpleLinkedList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private final SimpleLinkedList<T> simpleLinkedList = new SimpleLinkedList<>();

    public synchronized void add(T value) {
        simpleLinkedList.add(value);
    }

    public synchronized T get(int index) {
        return simpleLinkedList.get(index);
    }

    private synchronized List<T> copy(SimpleLinkedList<T> simpleLinkedList) {
        List<T> list = new ArrayList();
        simpleLinkedList.forEach(list::add);
        return list;
    }

    @Override
    public Iterator<T> iterator() {
        return copy(this.simpleLinkedList).iterator();
    }
}
