package org.example;


import java.util.concurrent.Semaphore;

public class Philosopher extends Thread {
    private final String name;
    private Fork leftFork;
    private Fork rightFork;
    private final int numberOfMeals;
    private int countOfMeals;
    private Semaphore semaphore;

    public Philosopher(String name, Fork leftFork, Fork rightFork, Semaphore semaphore) {
        this.name = name;
        numberOfMeals = 3;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            while (countOfMeals < numberOfMeals) {
                System.out.println(name + " думает...");
                Thread.sleep(1000);
                semaphore.acquire();  // Получаем разрешение на обед
                synchronized (leftFork) {
                    System.out.println(name + " взял левую " + leftFork.getName());
                    synchronized (rightFork) {
                        System.out.println(name + " взял правую " + rightFork.getName());
                        System.out.println(name + " кушает " + (countOfMeals + 1) + "й раз");
                        Thread.sleep(1000);
                        System.out.println(name + " положил правую " + rightFork.getName());
                    }

                    System.out.println(name + " положил левую " + leftFork.getName());
                }
                semaphore.release(); // Освобождаем очередь на обед
                countOfMeals++;
            }
            System.out.println(name + " закончил поток.");
        } catch (InterruptedException e) {
                throw new RuntimeException(e);
        }

    }
}
