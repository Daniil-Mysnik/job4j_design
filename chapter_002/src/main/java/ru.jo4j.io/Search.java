package ru.jo4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER, FILE_EXTENSION");
        }
        search(Paths.get(args[0]), args[1]).forEach(System.out::println);
    }

    public static List<Path> search(Path root, String ext) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> p.toFile().getName().endsWith(ext));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static List<Path> searchAndExclude(Path root, String ext) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> !p.toFile().getName().endsWith(ext));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static List<Path> searchByMask(Path root, String mask) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> p.toFile().getName().endsWith(mask));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static List<Path> searchByFullName(Path root, String fileName) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> p.toFile().getName().equals(fileName));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static List<Path> searchByRegExp(Path root, String regExp) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> p.toFile().getName().matches(regExp));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
