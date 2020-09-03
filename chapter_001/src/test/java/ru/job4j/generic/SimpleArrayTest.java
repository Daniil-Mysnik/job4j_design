package ru.job4j.generic;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class SimpleArrayTest {
    @Test
    public void testAdd() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.add(100);
        assertEquals(100, (int) simpleArray.get(0));
    }

    @Test
    public void testAddSomeElements() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(5);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        assertEquals(1, (int) simpleArray.get(0));
        assertEquals(2, (int) simpleArray.get(1));
        assertEquals(3, (int) simpleArray.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddInFullArray() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(1);
        simpleArray.add(1);
        simpleArray.add(2);
    }

    @Test
    public void testSet() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.add(10);
        simpleArray.set(0, 100);
        assertEquals(100, (int) simpleArray.get(0));
        simpleArray.set(1, 200);
        assertEquals(200, (int) simpleArray.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetIOOB() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.set(100, 0);
    }

    @Test
    public void testRemove() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.add(1);
        simpleArray.remove(0);
        assertNull(simpleArray.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIOOB() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.remove(100);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetIOOB() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.get(100);
    }

    @Test
    public void testSimpleArrayIterator() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.add(1);
        simpleArray.set(9, 10);
        Iterator<Integer> iterator = simpleArray.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1, (int) iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(10, (int) iterator.next());
    }

    @Test(expected = NoSuchElementException.class)
    public void testSimpleArrayIteratorNSEE() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        Iterator<Integer> iterator = simpleArray.iterator();
        iterator.next();
    }
}
