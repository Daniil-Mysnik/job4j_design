package ru.jo4j.io.console_chat;

public class Application {
    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat(new Answers(),
                new Logger(),
                "answers.txt",
                "answersLog.txt");
        consoleChat.toChat();
    }
}
