package ru.jo4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(source));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(target))) {
            List<String[]> list = new ArrayList<>();
            bufferedReader.lines()
                    .map(s -> s.split(" "))
                    .forEach(list::add);
            boolean flag = false;
            StringBuilder sb = new StringBuilder();
            for (String[] strings : list) {
                boolean available = strings[0].equals("200") || strings[0].equals("300");
                if (!flag && !available) {
                    sb.append(strings[1]).append(" - ");
                    flag = true;
                } else if (flag && available) {
                    sb.append(strings[1]).append(System.lineSeparator());
                    bufferedWriter.write(sb.toString());
                    sb.setLength(0);
                    flag = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
