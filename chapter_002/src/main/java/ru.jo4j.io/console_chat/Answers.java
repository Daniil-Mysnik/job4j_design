package ru.jo4j.io.console_chat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Answers {
    public List<String> save(String fileName) {
        List<String> answers = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)))) {
            bufferedReader.lines().forEach(answers::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answers;
    }

    public String getRandomAnswer(List<String> answers) {
        return answers.get(new Random().nextInt(answers.size()));
    }

}
