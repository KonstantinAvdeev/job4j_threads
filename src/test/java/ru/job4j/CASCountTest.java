package ru.job4j;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;

class CASCountTest {
    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        AtomicReference<Integer> atomicReference = new AtomicReference<>(0);
        var count = new CASCount(atomicReference);
        var first = new Thread(count::increment);
        var second = new Thread(count::increment);
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(count.get()).isEqualTo(2);
    }
}
