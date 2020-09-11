package ru.jo4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < 11; i++) {
                for (int j = 1; j < 11; j++) {
                    sb.append((i * j));
                }
                sb.append("\n");
            }
            out.write(sb.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
