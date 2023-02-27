package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count;

    public CASCount(AtomicReference<Integer> count) {
        this.count = count;
    }

    public void increment() {
        int ref;
        int res;
        do {
            ref = get();
            res = ref + 1;
        } while (!count.compareAndSet(ref, res));
    }

    public int get() {
        return count.get();
    }

}