package ru.job4j.pool;

import ru.job4j.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();
    int size = Runtime.getRuntime().availableProcessors();

    public ThreadPool() {
        for (int i = 0; i < size; i++) {
            threads.add(new Thread(() ->
            {
                try {
                    tasks.poll().run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }));
        }
        threads.forEach(Thread::start);
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        for (int i = 0; i < 10; i++) {
            threadPool.work(() -> System.out.println("Hello World!"));
        }
        threadPool.shutdown();
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }

}