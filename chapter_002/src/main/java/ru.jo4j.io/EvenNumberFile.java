package ru.jo4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder sb = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                sb.append((char) read);
            }
            String[] strings = sb.toString().split("\r\n");
            for (String s : strings) {
                System.out.println(s + " is " + (Integer.parseInt(s) % 2 == 0 ? "even" : "odd"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
