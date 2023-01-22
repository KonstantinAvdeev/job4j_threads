package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(
                () -> {
                    System.out.println("FIRST THREAD");
                }
        );
        first.start();
        if (first.getState() != Thread.State.TERMINATED) {
            first.join();
        }
        Thread second = new Thread(
                () -> {
                    System.out.println("SECOND THREAD");
                }
        );
        second.start();
        if (second.getState() != Thread.State.TERMINATED) {
            second.join();
        }
        System.out.println("ALL THREADS TERMINATED");
    }

}