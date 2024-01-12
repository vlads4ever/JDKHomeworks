package org.example.ver2;

public class Philosopher2 extends Thread {
    private final String name;
    private Fork2 leftFork;
    private Fork2 rightFork;
    private final int numberOfMeals;
    private int countOfMeals;

    public Philosopher2(String name, Fork2 leftFork, Fork2 rightFork) {
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
                if (!leftFork.isTaken()) {
                    leftFork.setTaken(true);
                    System.out.println(name + " взял левую " + leftFork.getName());
                    if (!rightFork.isTaken()) {
                        rightFork.setTaken(true);
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
