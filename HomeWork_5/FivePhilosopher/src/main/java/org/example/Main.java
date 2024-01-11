package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит тарелка спагетти.
 * Вилки лежат на столе между каждой парой ближайших философов.
 * Каждый философ может либо есть, либо размышлять.
 * Философ может есть только тогда, когда держит две вилки — взятую справа и слева.
 * Философ не может есть два раза подряд, не прервавшись на размышления (можно не учитывать)
 * Описать в виде кода такую ситуацию. Каждый философ должен поесть три раза
 */
public class Main {
    private static final int philosophersNumber = 5;
    private static final int dinerNumber = philosophersNumber / 2;
    private static final Semaphore semaphore = new Semaphore(dinerNumber);
    private static Fork[] forks = new Fork[philosophersNumber];

    public static void main(String[] args) {
        for (int i = 0; i < philosophersNumber; i++) {
            forks[i] = new Fork("Вилка №" + (i + 1));
        }

        for (int i = 0; i < philosophersNumber; i++) {
            Fork leftFork = forks[i];
            Fork rightFork = (i < philosophersNumber - 1) ? forks[i + 1] : forks[0];
            new Philosopher("Философ №" + (i + 1), leftFork, rightFork, semaphore).start();
        }
    }
}