package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        String[] strings = {"-", "\\", "|", "/"};
        int index = 0;
        while (!Thread.currentThread().isInterrupted()) {
            System.out.print("\r" + strings[index]);
            if (index < 3) {
                index++;
            } else {
                index = 0;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.print("\rFinished");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(10000); /* симулируем выполнение параллельной задачи в течение 1 секунды. */
        progress.interrupt();
    }
}
