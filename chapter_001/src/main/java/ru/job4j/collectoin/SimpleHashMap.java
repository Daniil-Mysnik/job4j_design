package ru.job4j.collectoin;

import java.util.*;

public class SimpleHashMap<K, V> implements Iterable<V> {
    private int capacity = 16;
    private Object[] container;
    private int modCount = 0;
    private final float DEFAULT_LOAD_FACTOR = 0.75F;
    private float loadFactor;
    private int size = 0;

    public SimpleHashMap() {
        this.container = new Object[capacity];
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public SimpleHashMap(float loadFactor) {
        this.container = new Object[capacity];
        this.loadFactor = loadFactor;
    }

    public boolean insert(K key, V value) {
        int hashCode = hashCode(key);
        int bucket = indexOf(hashCode);
        Node<K, V> checkNode = ((Node<K, V>) container[bucket]);
        if (checkNode != null) {
            if (checkNode.hashCode == hashCode && ((checkNode.key) == key || (key != null && key.equals(checkNode.key)))) {
                checkNode.value = value;
                modCount++;
            }
            return false;
        }
        int threshold = (int) (capacity * loadFactor);
        if (size == threshold) {
            expansion();
        }
        Node<K, V> node = new Node<>(hashCode, key, value);
        container[bucket] = node;
        size++;
        modCount++;
        return true;
    }

    public V get(K key) {
        int bucket = indexOf(hashCode(key));
        if (container[bucket] == null) {
            throw new NoSuchElementException();
        }
        return ((Node<K, V>) container[bucket]).value;
    }

    public boolean delete(K key) {
        int bucket = indexOf(hashCode(key));
        if (container[bucket] != null) {
            container[bucket] = null;
            size--;
            modCount++;
        }
        return false;
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            int bucket = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                while (bucket <= size) {
                    if (container[bucket] != null) {
                        return true;
                    }
                    bucket++;
                }
                return false;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return ((Node<K,V>) container[bucket++]).value;
            }
        };
    }

    private void expansion() {
        capacity *= 2;
        Object[] newContainer = new Object[capacity];
        for (Object object : container) {
            if (object != null) {
                Node<K,V> node = ((Node<K, V>) object);
                int hashCode = hashCode(node.key);
                int bucket = indexOf(hashCode);
                node.hashCode = hashCode;
                newContainer[bucket] = node;
            }
        }
        container = newContainer;
    }

    private int hashCode(Object key) {
        int h;
        return key == null ? 0 : (h = key.hashCode()) ^ h >>> 16;
    }

    private int indexOf(int hashCode) {
        return hashCode & (capacity - 1);
    }

    private static class Node<K, V> {
        int hashCode;
        K key;
        V value;
        Node<K,V> next = null;

        public Node(int hashCode, K key, V value) {
            this.hashCode = hashCode;
            this.key = key;
            this.value = value;
        }
    }
}
