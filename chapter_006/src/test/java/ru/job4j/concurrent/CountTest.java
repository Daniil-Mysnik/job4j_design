package ru.job4j.concurrent;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CountTest {
    private class ThreadCount extends Thread {
        private Count count;

        private ThreadCount(Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            this.count.increment();
        }
    }

    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        //Создаем счетчик.
        Count count = new Count();
        //Создаем нити.
        Thread first = new ThreadCount(count);
        Thread second = new ThreadCount(count);
        //Запускаем нити.
        first.start();
        second.start();
        //Заставляем главную нить дождаться выполнения наших нитей.
        first.join();
        second.join();
        //Проверяем результат.
        assertEquals(count.get(), 2);

    }
}