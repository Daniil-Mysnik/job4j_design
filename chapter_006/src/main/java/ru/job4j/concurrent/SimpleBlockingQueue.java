package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private volatile Queue<T> queue = new LinkedList<>();
    private final int capacity;

    public SimpleBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void offer(T value) {
        synchronized (this) {
            while (queue.size() == capacity) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            if (!Thread.currentThread().isInterrupted()) {
                queue.add(value);
                notify();
            }
        }
    }

    public T poll() throws InterruptedException {
        synchronized (this) {
            while (queue.isEmpty()) {
                if (!Thread.interrupted()) {
                    wait();
                }
            }
            notify();
            return queue.poll();
        }
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void getQueue() {
        queue.forEach(System.out::println);
    }

    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> sbq = new SimpleBlockingQueue<>(3);
        Thread thread = new Thread(
                () -> {
                    sbq.offer(5);
                    sbq.offer(10);
                }
        );
        Thread thread2 = new Thread(
                () -> {
                    sbq.offer(30);
                    try {
                        sbq.poll();
                        sbq.poll();
                        sbq.poll();
                        sbq.poll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sbq.offer(40);
                    sbq.getQueue();
                }
        );
        Thread thread3 = new Thread(
                () -> {
                    sbq.offer(50);
                }
        );
        thread.start();
        thread2.start();
        thread3.start();
        //WILL RETURN 40;
    }
}
