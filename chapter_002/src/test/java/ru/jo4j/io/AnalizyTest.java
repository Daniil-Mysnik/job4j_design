package ru.jo4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class AnalizyTest {
    @Test
    public void testAnalizy() {
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/server.log", "./data/target.txt");
        List<String> actual = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("./data/target.txt"))) {
            actual = reader.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> expected = Arrays.asList("10:57:01 - 10:59:01", "11:01:02 - 11:02:02");
        assertEquals(expected, actual);
    }
}