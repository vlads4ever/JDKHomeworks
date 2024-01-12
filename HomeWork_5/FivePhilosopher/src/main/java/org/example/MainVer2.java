package org.example;

import org.example.ver2.Fork2;
import org.example.ver2.Philosopher2;

/**
 * Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит тарелка спагетти.
 * Вилки лежат на столе между каждой парой ближайших философов.
 * Каждый философ может либо есть, либо размышлять.
 * Философ может есть только тогда, когда держит две вилки — взятую справа и слева.
 * Философ не может есть два раза подряд, не прервавшись на размышления (можно не учитывать)
 * Описать в виде кода такую ситуацию. Каждый философ должен поесть три раза
 */
public class MainVer2 {
    // Второй вариант решения с помощью волатильной переменной признака занятости вилки
    private static final int philosophersNumber = 5;
    private static Fork2[] forks = new Fork2[philosophersNumber];

    public static void main(String[] args) {
        for (int i = 0; i < philosophersNumber; i++) {
            forks[i] = new Fork2("Вилка №" + (i + 1));
        }

        for (int i = 0; i < philosophersNumber; i++) {
            Fork2 leftFork = forks[i];
            Fork2 rightFork = (i < philosophersNumber - 1) ? forks[i + 1] : forks[0];
            new Philosopher2("Философ №" + (i + 1), leftFork, rightFork).start();
        }
    }
}
