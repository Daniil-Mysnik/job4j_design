package ru.jo4j.io;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

public class ArgZip {
    private String directory;
    private String exclude;
    private String output;

    public ArgZip(String[] args) {
        valid(args);
    }

    private boolean valid(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("There must be at least 2 parameters: -d (directory), -o (output)");
        }
        for (String str : args) {
            String[] strings = str.split("=");
            switch (strings[0]) {
                case "-d":
                    if (!new File(strings[1]).exists()) {
                        System.out.println("Directory " + strings[1] + " not exist");
                        return false;
                    } else {
                        this.directory = strings[1];
                        break;
                    }
                case "-o":
                    this.output = strings[1];
                    break;
                case "-e":
                    this.exclude = strings[1];
                    break;
                default:
                    throw new IllegalArgumentException(strings[0] + " - wrong parameter!!!");
            }
        }
        return true;
    }

    public String directory() {
        return this.directory;
    }

    public String exclude() {
        return this.exclude;
    }

    public String output() {
        return this.output;
    }

}
