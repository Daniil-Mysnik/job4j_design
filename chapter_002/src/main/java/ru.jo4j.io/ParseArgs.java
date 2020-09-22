package ru.jo4j.io;

import java.io.File;
import java.nio.file.Path;

public class ParseArgs {
    private Path directoryPath;
    private String fileName;
    private boolean mask;
    private boolean fullName;
    private boolean regExp;
    private String logFile;

    public ParseArgs(String[] args) {
        validArgs(args);
    }

    private void validArgs(String[] args) {
        if (args.length != 7) {
            throw new IllegalArgumentException("7 arguments are needed: \n" +
                    "-d - directory where to start searching.\n" +
                    "-n - file name, mask, or regular expression.\n" +
                    "-m - search by mask, or -f - full name match, or -r regular expression.\n" +
                    "-o - write the result to a file.");
        }
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-d":
                    directoryPath = checkDirectory(args[i + 1]);
                    i++;
                    break;
                case "-n":
                    fileName = args[i + 1];
                    i++;
                    break;
                case "-m":
                    mask = true;
                    fullName = false;
                    regExp = false;
                    fileName = fileName.replace("*", "");
                    break;
                case "-f":
                    mask = false;
                    fullName = true;
                    regExp = false;
                    break;
                case "-r":
                    mask = false;
                    fullName = false;
                    regExp = true;
                    break;
                case "-o":
                    logFile = args[i + 1];
                    i++;
                    break;
                default: throw new IllegalArgumentException("Wrong argument " + args[i]);
            }
        }
    }

    private Path checkDirectory(String path) {
        File file = new File(path);
        if (!file.exists()) {
            throw new IllegalArgumentException("Directory " + path + " not exist!");
        } else if (!file.isDirectory()) {
            throw new IllegalArgumentException(path + " it is not directory!");
        }
        return file.toPath();
    }

    public Path getDirectoryPath() {
        return directoryPath;
    }

    public String getFileName() {
        return fileName;
    }

    public boolean isMask() {
        return mask;
    }

    public boolean isFullName() {
        return fullName;
    }

    public boolean isRegExp() {
        return regExp;
    }

    public String getLogFile() {
        return logFile;
    }

}
