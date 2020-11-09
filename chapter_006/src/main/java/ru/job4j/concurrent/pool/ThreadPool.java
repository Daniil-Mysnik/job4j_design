package ru.job4j.concurrent.pool;

import ru.job4j.concurrent.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final int size = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(10);

    public ThreadPool() {
        for (int i = 0; i < size; i++) {
            threads.add(new Thread(new Task(tasks)));
        }
        threads.forEach(Thread::start);
    }

    public void work(Runnable job) {
        tasks.offer(job);
    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }

    private static final class Task implements Runnable {
        private final SimpleBlockingQueue<Runnable> tasks;

        private Task(SimpleBlockingQueue<Runnable> tasks) {
            this.tasks = tasks;
        }

        @Override
        public void run() {
            while (!tasks.isEmpty() || !Thread.currentThread().isInterrupted()) {
                try {
                    //For debugging. So that threads do not immediately disassemble tasks
                    //Thread.sleep(10000);
                    new Thread(tasks.poll()).start();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threadPool.work(() -> {
                System.out.println("Task " + finalI);
            });
        }
        threadPool.shutdown();
    }
}
