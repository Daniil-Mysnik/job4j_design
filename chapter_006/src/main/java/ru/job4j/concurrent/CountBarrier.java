package ru.job4j.concurrent;

public class CountBarrier {
    private final Object monitor = this;
    private final int total;
    private volatile int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            count++;
            monitor.notifyAll();
        }
    }

    public void await() {
        synchronized (monitor) {
            while (total != count) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        CountBarrier countBarrier = new CountBarrier(3);
        Thread thread = new Thread(
                () -> {
                    Thread.currentThread().setName("First");
                    System.out.println(Thread.currentThread().getName());
                    countBarrier.count();
                    countBarrier.count();
                    System.out.println(countBarrier.getCount());
                }
        );
        Thread thread3 = new Thread(
                () -> {
                    Thread.currentThread().setName("Third");
                    System.out.println(Thread.currentThread().getName());
                    countBarrier.count();
                    System.out.println(countBarrier.getCount());
                }
        );
        Thread thread2 = new Thread(
                () -> {
                    countBarrier.await();
                    Thread.currentThread().setName("Second");
                    System.out.println(Thread.currentThread().getName());
                }
        );
        thread.start();
        thread2.start();
        thread3.start();
    }
}
