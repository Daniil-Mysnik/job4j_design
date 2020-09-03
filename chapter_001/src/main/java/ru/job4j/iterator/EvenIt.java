package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIt implements Iterator<Integer> {
    private final int[] nums;
    private int point = 0;

    public EvenIt(int[] nums) {
        this.nums = nums;
    }

    @Override
    public boolean hasNext() {
        while (point < nums.length) {
            if (nums[point] % 2 == 0) {
                return true;
            }
            point++;
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return nums[point++];
    }
}
