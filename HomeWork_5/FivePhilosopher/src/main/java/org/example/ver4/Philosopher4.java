package org.example.ver4;

public class Philosopher4 extends Thread {
    private final String name;
    private Fork4 leftFork;
    private Fork4 rightFork;
    private final int numberOfMeals;
    private int countOfMeals;

    public Philosopher4(String name, Fork4 leftFork, Fork4 rightFork) {
        this.name = name;
        numberOfMeals = 3;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        try {
            while (countOfMeals < numberOfMeals) {
                System.out.println(name + " думает...");
                Thread.sleep(1000);
                // Если вилка свободна (false), то compareAndSet вернет true и признак занятости вилки поменяется на true,
                // а если вилка занята (true), то compareAndSet вернет false и признак занятости вилки НЕ поменяется на true
                if (leftFork.getTaken().compareAndSet(false, true)) {
                    System.out.println(name + " взял левую " + leftFork.getName());
                    if (rightFork.getTaken().compareAndSet(false, true)) {
                        System.out.println(name + " взял правую " + rightFork.getName());
                        System.out.println(name + " вкушает " + (countOfMeals + 1) + "-й раз");
                        Thread.sleep(1000);
                        rightFork.setTaken(false);
                        System.out.println(name + " положил правую " + rightFork.getName());
                        countOfMeals++;
                    }
                    leftFork.setTaken(false);
                    System.out.println(name + " положил левую " + leftFork.getName());
                }
            }
            System.out.println(name + " закончил поток.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
