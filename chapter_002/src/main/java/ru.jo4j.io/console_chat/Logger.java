package ru.jo4j.io.console_chat;

import java.io.*;
import java.util.List;

public class Logger {
    public void saveLog(List<String> log, String fileName) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(fileName)))) {
            for (String s : log) {
                bufferedWriter.write(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
