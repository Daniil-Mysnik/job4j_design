package ru.job4j.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoin extends RecursiveTask<Integer> {
    private final Object[] array;
    private final int from;
    private final int to;
    private final Object sourceElement;

    public ForkJoin(Object[] array, int from, int to, Object sourceElement) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.sourceElement = sourceElement;
    }

    private int linearSearch() {
        for (int i = from; i < to; i++) {
            if (array[i].equals(sourceElement)) {
                return i;
            }
        }
        return -1;
    }

    private int parallelSearch() {
        int mid = (from + to) / 2;

        ForkJoin leftFork = new ForkJoin(array, from, mid, sourceElement);
        ForkJoin rightFork = new ForkJoin(array, mid + 1, to, sourceElement);

        leftFork.fork();
        rightFork.fork();

        return Math.max(leftFork.join(), rightFork.join());
    }

    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            return linearSearch();
        }
        return parallelSearch();
    }

    public static void main(String[] args) {
        ForkJoin forkJoin = new ForkJoin(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}, 0, 11, 8);
        System.out.println(new ForkJoinPool().invoke(forkJoin));
    }

}
