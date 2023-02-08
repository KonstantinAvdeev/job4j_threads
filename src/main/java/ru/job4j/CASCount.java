package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        int ref;
        int res;
        do {
            ref = get();
            res = ref++;
        } while (!count.compareAndSet(ref, res));
    }

    public int get() {
        return count.get();
    }
}