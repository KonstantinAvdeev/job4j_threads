package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelArraySearch extends RecursiveTask<Integer> {
    private final Object[] array;
    private final int from;
    private final int to;
    private final Object object;

    public ParallelArraySearch(Object[] array, int from, int to, Object object) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.object = object;
    }

    public static int search(Object[] array, Object o) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelArraySearch(array, 0, array.length - 1, o));
    }

    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            return ArraySearch.index(array, object);
        }
        int mid = (from + to) / 2;
        ParallelArraySearch left = new ParallelArraySearch(array, from, mid, object);
        ParallelArraySearch right = new ParallelArraySearch(array, mid + 1, to, object);
        left.fork();
        right.fork();
        int leftIndex = left.join();
        int rightIndex = right.join();
        return Math.max(leftIndex, rightIndex);
    }

}
