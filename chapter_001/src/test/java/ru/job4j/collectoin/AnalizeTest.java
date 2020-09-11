package ru.job4j.collectoin;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AnalizeTest {

    @Test
    public void testAnalizeWhen1Added1Changed1Deleted() {
        List<Analize.User> previous = Arrays.asList(new Analize.User(1, "Michael"),
                new Analize.User(2, "Vova"),
                new Analize.User(3, "Andrew"),
                new Analize.User(4, "Nikita"));
        List<Analize.User> current = Arrays.asList(new Analize.User(1, "Michael"),
                new Analize.User(2, "Vladimir"),
                new Analize.User(3, "Andrew"),
                new Analize.User(5, "Slava"));
        System.out.println(new Analize().diff(previous, current).toString());
        assertEquals(new Analize.Info(1, 1, 1), new Analize().diff(previous, current));
    }

    @Test
    public void testAnalizeWhen1Changed() {
        List<Analize.User> previous = Arrays.asList(new Analize.User(1, "Michael"),
                new Analize.User(2, "Vova"),
                new Analize.User(3, "Andrew"),
                new Analize.User(4, "Nikita"));
        List<Analize.User> current = Arrays.asList(new Analize.User(1, "Michael"),
                new Analize.User(2, "Vladimir"),
                new Analize.User(3, "Andrew"),
                new Analize.User(4, "Nikita"));
        System.out.println(new Analize().diff(previous, current).toString());
        assertEquals(new Analize.Info(0, 1, 0), new Analize().diff(previous, current));
    }

    @Test
    public void testWhenNothingChanged() {
        List<Analize.User> previous = Arrays.asList(new Analize.User(1, "Michael"),
                new Analize.User(2, "Vova"),
                new Analize.User(3, "Andrew"),
                new Analize.User(4, "Nikita"));
        List<Analize.User> current = Arrays.asList(new Analize.User(1, "Michael"),
                new Analize.User(2, "Vova"),
                new Analize.User(3, "Andrew"),
                new Analize.User(4, "Nikita"));
        System.out.println(new Analize().diff(previous, current).toString());
        assertEquals(new Analize.Info(0, 0, 0), new Analize().diff(previous, current));
    }

    @Test
    public void testWhenAllDeleted() {
        List<Analize.User> previous = Arrays.asList(new Analize.User(1, "Michael"),
                new Analize.User(2, "Vova"),
                new Analize.User(3, "Andrew"),
                new Analize.User(4, "Nikita"));
        List<Analize.User> current = new ArrayList<>();
        System.out.println(new Analize().diff(previous, current).toString());
        assertEquals(new Analize.Info(0, 0, 4), new Analize().diff(previous, current));
    }

    @Test
    public void testWhenAllAdded() {
        List<Analize.User> previous = new ArrayList<>();
        List<Analize.User> current = Arrays.asList(new Analize.User(1, "Michael"),
                new Analize.User(2, "Vova"),
                new Analize.User(3, "Andrew"),
                new Analize.User(4, "Nikita"));
        System.out.println(new Analize().diff(previous, current).toString());
        assertEquals(new Analize.Info(4, 0, 0), new Analize().diff(previous, current));
    }

    @Test
    public void testWhen3Deleted1Changed() {
        List<Analize.User> previous = Arrays.asList(new Analize.User(1, "Michael"),
                new Analize.User(2, "Vova"),
                new Analize.User(3, "Andrew"),
                new Analize.User(4, "Nikita"));
        List<Analize.User> current = Arrays.asList(new Analize.User(2, "Vladimir"));
        System.out.println(new Analize().diff(previous, current).toString());
        assertEquals(new Analize.Info(0, 1, 3), new Analize().diff(previous, current));
    }

}