package ru.jo4j.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class SearchByCriteria {
    public static void main(String[] args) throws IOException {
        SearchByCriteria.search(args);
    }

    public static void search(String[] args) throws IOException {
        ParseArgs parseArgs = new ParseArgs(args);
        List<Path> paths;
        if (parseArgs.isMask()) {
            paths = Search.searchByMask(parseArgs.getDirectoryPath(), parseArgs.getFileName());
        } else if (parseArgs.isFullName()) {
            paths = Search.searchByFullName(parseArgs.getDirectoryPath(), parseArgs.getFileName());
        } else {
            paths = Search.searchByRegExp(parseArgs.getDirectoryPath(), parseArgs.getFileName());
        }
        try (FileWriter fileWriter = new FileWriter(new File(parseArgs.getLogFile()))) {
                paths.forEach(path -> {
                    try {
                        fileWriter.write(path.toString() + System.lineSeparator());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
