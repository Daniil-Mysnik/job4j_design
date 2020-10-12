package ru.job4j.concurrent;

import java.util.Arrays;

public class Arg {
    private String url;
    private Integer speed;

    public Arg(String[] args) {
        valid(args);
        this.url = getUrlFromArgs(args);
        this.speed = getSpeedFromArgs(args);
    }

    public void valid(String[] args) {
        if (args.length == 0)
            throw new IllegalArgumentException("Args is null. Usage java -jar -url ... - speed...");
    }

    public String getUrlFromArgs(String[] args) {
        if (!Arrays.toString(args).contains("-url")) {
            throw new IllegalArgumentException("-url dont exist");
        }
        String url = "";
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("-url")) {
                url = args[i + 1];
                break;
            }
        }
        return url;
    }

    public Integer getSpeedFromArgs(String[] args) {
        if (!Arrays.toString(args).contains("-speed")) {
            throw new IllegalArgumentException("-speed dont exist");
        }
        int speed = 0;
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("-speed")) {
                speed = Integer.parseInt(args[i + 1]);
                break;
            }
        }
        return speed;
    }

    public String getUrlFromArgs() {
        return url;
    }

    public Integer getSpeedFromArgs() {
        return speed;
    }

}
