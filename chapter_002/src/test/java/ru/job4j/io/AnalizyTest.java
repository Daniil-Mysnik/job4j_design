package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.jo4j.io.Analizy;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

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

    @Test
    public void testAnalizyWithTemporaryFolder() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01\n" +
                    "500 10:57:01\n" +
                    "400 10:58:01\n" +
                    "200 10:59:01\n" +
                    "500 11:01:02\n" +
                    "200 11:02:02\n" +
                    "500 11:03:01\n" +
                    "400 11:04:01\n" +
                    "300 11:05:01\n");
        }
        new Analizy().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        List<String> expected = Arrays.asList("10:57:01 - 10:59:01", "11:01:02 - 11:02:02", "11:03:01 - 11:05:01");
        List<String> actual = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(actual::add);
        }
        assertEquals(expected, actual);
    }
}