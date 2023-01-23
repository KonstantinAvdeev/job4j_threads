package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> {
                    System.out.println("FIRST THREAD");
                }
        );
        first.start();
        Thread second = new Thread(
                () -> {
                    System.out.println("SECOND THREAD");
                }
        );
        second.start();
        while (first.getState() != Thread.State.TERMINATED || second.getState() != Thread.State.TERMINATED) {
            System.out.print("");
        }
        System.out.println("ALL THREADS TERMINATED");
    }
}