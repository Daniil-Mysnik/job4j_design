package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;

public class FileDownload {

    private static void readFile(String url, int speedLimit) {
        long startTime = System.currentTimeMillis();
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            long endTime = System.currentTimeMillis();
            int delay = 0;
            int countSec = (int) ((int) endTime - startTime);
            if (countSec > 1)
                delay = countSec / speedLimit;
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                Thread.sleep(delay);
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
       Arg arg = new Arg(args);
       readFile(arg.getUrlFromArgs(), arg.getSpeedFromArgs());
    }
}
