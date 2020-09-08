package ru.job4j.collectoin;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SimpleHashMapTest {
    @Test
    public void testInsertOneElement() {
        SimpleHashMap simpleHashMap = new SimpleHashMap();
        simpleHashMap.insert(1, "One");
        assertEquals("One", simpleHashMap.get(1));
    }

    @Test
    public void testWipeElement() {
        SimpleHashMap simpleHashMap = new SimpleHashMap();
        simpleHashMap.insert(1, "One");
        simpleHashMap.insert(1, "Two");
        assertEquals("Two", simpleHashMap.get(1));
    }

    @Test
    public void testExpansion() {
        SimpleHashMap simpleHashMap = new SimpleHashMap();
        simpleHashMap.insert(1, "One");
        simpleHashMap.insert(2, "Two");
        simpleHashMap.insert(3, "Three");
        simpleHashMap.insert(4, "Four");
        simpleHashMap.insert(5, "Five");
        simpleHashMap.insert(6, "Six");
        simpleHashMap.insert(7, "Seven");
        simpleHashMap.insert(8, "Eight");
        simpleHashMap.insert(9, "Nine");
        simpleHashMap.insert(10, "Ten");
        simpleHashMap.insert(11, "Eleven");
        simpleHashMap.insert(12, "Twelve");
        simpleHashMap.insert(13, "Thirteen");
        simpleHashMap.insert(14, "Fourteen");
        simpleHashMap.insert(15, "Fifteen");
        simpleHashMap.insert(16, "Sixteen");
        simpleHashMap.insert(17, "Seventeen");
        simpleHashMap.insert(18, "Eighteen");
        assertEquals("Two", simpleHashMap.get(2));
        assertEquals("Seven", simpleHashMap.get(7));
        assertEquals("Fourteen", simpleHashMap.get(14));
        assertEquals("Seventeen", simpleHashMap.get(17));
        assertEquals("Eighteen", simpleHashMap.get(18));
    }

    @Test(expected = NoSuchElementException.class)
    public void testNonExistElement() {
        SimpleHashMap simpleHashMap = new SimpleHashMap();
        simpleHashMap.insert(1, "One");
        simpleHashMap.insert(2, "Two");
        simpleHashMap.get(10);
    }

    @Test(expected = NoSuchElementException.class)
    public void testDeleteElement() {
        SimpleHashMap simpleHashMap = new SimpleHashMap();
        simpleHashMap.insert(1, "One");
        simpleHashMap.insert(2, "Two");
        simpleHashMap.delete(1);
        simpleHashMap.get(1);
    }

    @Test
    public void testDeleteNonExistElement() {
        SimpleHashMap simpleHashMap = new SimpleHashMap();
        boolean rsl = simpleHashMap.delete(10);
        assertFalse(rsl);
    }

    @Test
    public void testIterator() {
        SimpleHashMap simpleHashMap = new SimpleHashMap();
        simpleHashMap.insert(1, "One");
        simpleHashMap.insert(2, "Two");
        Iterator<Integer> iterator = simpleHashMap.iterator();
        assertEquals("One", iterator.next());
        assertEquals("Two", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testIteratorWhenModify() {
        SimpleHashMap simpleHashMap = new SimpleHashMap();
        simpleHashMap.insert(1, "One");
        Iterator<Integer> iterator = simpleHashMap.iterator();
        simpleHashMap.insert(2, "Two");
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorNSEE() {
        SimpleHashMap simpleHashMap = new SimpleHashMap();
        simpleHashMap.iterator().next();
    }

}