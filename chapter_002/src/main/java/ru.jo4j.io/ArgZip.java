package ru.jo4j.io;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

public class ArgZip {
    private final String[] args;
    private HashMap<String, String> parameters = new HashMap<>();

    public ArgZip(String[] args) {
        this.args = args;
        Arrays.stream(args).forEach(s -> {
                    String[] strings = s.split("=");
                    parameters.put(strings[0], strings[1]);
                }
        );
    }

    public boolean valid() {
        boolean valid = true;
        if (!parameters.containsKey("-d")) {
            System.out.println("Parameter -d (directory) not exist!");
            valid = false;
        } else if (!new File(parameters.get("-d")).exists()) {
            System.out.println("Directory " + parameters.get("-d") + " not exist");
            valid = false;
        }
        if (!parameters.containsKey("-o")) {
            System.out.println("Parameter -o (output) not exist!");
            valid = false;
        }
        return valid;
    }

    public String directory() {
        return parameters.get("-d");
    }

    public String exclude() {
        return parameters.get("-e") != null ? parameters.get("-e") : "null";
    }

    public String output() {
        return parameters.get("-o");
    }

}
