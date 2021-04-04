package ru.geekbrains.java.level3.lesson4;

public class PrintABC {
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
        PrintABC printABC = new PrintABC();
        new Thread(() -> {
            printABC.printC();
        }).start();
        new Thread(() -> {
            printABC.printB();
        }).start();
        new Thread(() -> {
            printABC.printA();
        }).start();
    }
}
