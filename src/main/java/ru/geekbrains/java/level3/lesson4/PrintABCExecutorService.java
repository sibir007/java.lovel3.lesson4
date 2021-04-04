package ru.geekbrains.java.level3.lesson4;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrintABCExecutorService {
    private final Object lock = new Object();
    private char currentLetter = 'A';

    public void printA() {
        synchronized (lock) {
            for (int i = 0; i < 5; i++) {
                while (currentLetter != 'A'){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(currentLetter);
                currentLetter = 'B';
                lock.notifyAll();
            }
        }
    }
    public void printB() {
        synchronized (lock) {
            for (int i = 0; i < 5; i++) {
                while (currentLetter != 'B'){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(currentLetter);
                currentLetter = 'C';
                lock.notifyAll();
            }
        }
    }
    public void printC() {
        synchronized (lock) {
            for (int i = 0; i < 5; i++) {
                while (currentLetter != 'C'){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(currentLetter);
                currentLetter = 'A';
                lock.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        PrintABCExecutorService printABC = new PrintABCExecutorService();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(() -> {
            printABC.printC();
        });
        executorService.execute(() -> {
            printABC.printB();
        });
        executorService.execute(() -> {
            printABC.printA();
        });
        executorService.shutdown();
    }
}
