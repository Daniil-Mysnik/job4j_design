package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASСount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public CASСount() {
        count.set(0);
    }

    public void increment() {
        int value;
        int newValue;
        do {
            value = count.get();
            newValue = value + 1;
        } while (!count.compareAndSet(value, newValue));
    }

    public int get() {
        return count.get();
    }

    public static void main(String[] args) {
        CASСount casСount = new CASСount();
        Thread thread1 = new Thread(
                () -> {
                    casСount.increment();
                    System.out.println(casСount.get());
                    casСount.increment();
                    casСount.increment();
                    System.out.println(casСount.get());
                }
        );
        Thread thread2 = new Thread(
                () -> {
                    System.out.println(casСount.get());
                    casСount.increment();
                    casСount.increment();
                    casСount.increment();
                    System.out.println(casСount.get());
                }
        );
        thread1.start();
        thread2.start();
    }
}
