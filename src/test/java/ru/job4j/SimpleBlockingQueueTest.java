package ru.job4j;

import net.jcip.annotations.ThreadSafe;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@ThreadSafe
public class SimpleBlockingQueueTest {

    @Test
    public void whenProduceAndConsume() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(8);
        List<Integer> list = new LinkedList<>();
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 8; i++) {
                try {
                    queue.offer(i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        Thread consumer = new Thread(() -> {
            for (int i = 8; i > 0; i--) {
                try {
                    list.add(queue.poll());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(list.get(7), is(7));
        assertThat(list.get(5), is(5));
    }

}