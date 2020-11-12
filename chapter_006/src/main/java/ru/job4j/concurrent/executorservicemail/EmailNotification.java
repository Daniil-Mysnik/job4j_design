package ru.job4j.concurrent.executorservicemail;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );


    public void emailTo(User user) {
        pool.submit(
                () -> {
                    String subject = String.format("Notification {%s} to email {%s}", user.getUserName(), user.getEmail());
                    String body = String.format("Add a new event to {%s}", user.getUserName());
                    send(subject, body, user.getEmail());
                }
        );
    }

    public void send(String subject, String body, String email) {
        System.out.println(subject + System.lineSeparator() + body + System.lineSeparator() + email);
    }

    public void close() {
        pool.shutdown();
    }

    public static void main(String[] args) {
        User user = new User("Daniil", "Daniil@mail.ru");
        EmailNotification emailNotification = new EmailNotification();
        emailNotification.emailTo(user);
        emailNotification.close();
    }

}
