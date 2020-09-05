package ru.job4j.collectoin;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class SimpleLinkedListTest {
    @Test
    public void testAddOneElement() {
        SimpleLinkedList<Integer> simpleLinkedList = new SimpleLinkedList<>();
        simpleLinkedList.add(1);
        int actual = simpleLinkedList.get(0);
        assertEquals(1, actual);
    }

    @Test
    public void testAddSomeElements() {
        SimpleLinkedList<Integer> simpleLinkedList = new SimpleLinkedList<>();
        simpleLinkedList.add(1);
        simpleLinkedList.add(2);
        simpleLinkedList.add(3);
        assertEquals(1, (int) simpleLinkedList.get(0));
        assertEquals(2, (int) simpleLinkedList.get(1));
        assertEquals(3, (int) simpleLinkedList.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetWhenListEmpty() {
        SimpleLinkedList<Integer> simpleLinkedList = new SimpleLinkedList<>();
        simpleLinkedList.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetByWrongIndex() {
        SimpleLinkedList<Integer> simpleLinkedList = new SimpleLinkedList<>();
        simpleLinkedList.add(1);
        simpleLinkedList.get(100);
    }

    @Test
    public void testIterator() {
        SimpleLinkedList<Integer> simpleLinkedList = new SimpleLinkedList<>();
        simpleLinkedList.add(1);
        simpleLinkedList.add(2);
        simpleLinkedList.add(3);
        Iterator<Integer> iterator = simpleLinkedList.iterator();
        assertEquals(1, (int) iterator.next());
        assertEquals(2, (int) iterator.next());
        assertEquals(3, (int) iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testIteratorCME() {
        SimpleLinkedList<Integer> simpleLinkedList = new SimpleLinkedList<>();
        simpleLinkedList.add(1);
        Iterator<Integer> iterator = simpleLinkedList.iterator();
        simpleLinkedList.add(2);
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorNSEE() {
        SimpleLinkedList<Integer> simpleLinkedList = new SimpleLinkedList<>();
        Iterator<Integer> iterator = simpleLinkedList.iterator();
        iterator.next();
    }
}