package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelArraySearch<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final int from;
    private final int to;
    private final T object;

    public ParallelArraySearch(T[] array, int from, int to, T object) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.object = object;
    }

    public static int parallelSearch(Object[] array, Object o) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelArraySearch<>(array, 0, array.length - 1, o));
    }

    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            return lineSearch();
        }
        int mid = (from + to) / 2;
        ParallelArraySearch<T> left = new ParallelArraySearch<>(array, from, mid, object);
        ParallelArraySearch<T> right = new ParallelArraySearch<>(array, mid + 1, to, object);
        left.fork();
        right.fork();
        int leftIndex = left.join();
        int rightIndex = right.join();
        return Math.max(leftIndex, rightIndex);
    }

    private int lineSearch() {
        for (int i = from; i <= to; i++) {
            if (object.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

}