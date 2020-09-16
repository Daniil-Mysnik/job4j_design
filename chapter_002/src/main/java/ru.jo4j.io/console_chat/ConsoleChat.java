package ru.jo4j.io.console_chat;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private Answers answers;
    private Logger logger;
    private String answersFileName;
    private String logFileName;

    public ConsoleChat(Answers answers, Logger logger, String answersFileName, String logFileName) {
        this.answers = answers;
        this.logger = logger;
        this.answersFileName = answersFileName;
        this.logFileName = logFileName;
    }

    public void toChat() {
        List<String> answers = this.answers.save(answersFileName);
        String stopWord = "Стоп";
        String continueWord = "Продолжить";
        String exitWord = "Закончить";
        String temp = "";
        boolean botQueue = false;
        boolean stopped = false;
        List<String> log = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (!temp.equalsIgnoreCase(exitWord)) {
            if (!botQueue) {
                temp = scanner.nextLine();
                if(temp.equalsIgnoreCase(stopWord)) {
                    stopped = true;
                } else if (temp.equalsIgnoreCase(continueWord)) {
                    stopped = false;
                }
                log.add("User: " + temp + System.lineSeparator());
                if (!stopped) {
                    botQueue = true;
                }
            } else {
                temp = this.answers.getRandomAnswer(answers);
                System.out.println(temp);
                log.add("Bot: " + temp + System.lineSeparator());
                botQueue = false;
            }
        }
        logger.saveLog(log, logFileName);
    }
    
}
