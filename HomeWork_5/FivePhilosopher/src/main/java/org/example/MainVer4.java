package org.example;

import org.example.ver4.Fork4;
import org.example.ver4.Philosopher4;

/**
 * Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит тарелка спагетти.
 * Вилки лежат на столе между каждой парой ближайших философов.
 * Каждый философ может либо есть, либо размышлять.
 * Философ может есть только тогда, когда держит две вилки — взятую справа и слева.
 * Философ не может есть два раза подряд, не прервавшись на размышления (можно не учитывать)
 * Описать в виде кода такую ситуацию. Каждый философ должен поесть три раза
 */
public class MainVer4 {
    // Четвертый вариант решения с помощью атомарного признака занятости вилки
    private static final int philosophersNumber = 2;
    private static Fork4[] forks = new Fork4[philosophersNumber];

    public static void main(String[] args) {
        for (int i = 0; i < philosophersNumber; i++) {
            forks[i] = new Fork4("Вилка №" + (i + 1));
        }

        for (int i = 0; i < philosophersNumber; i++) {
            Fork4 leftFork = forks[i];
            Fork4 rightFork = (i < philosophersNumber - 1) ? forks[i + 1] : forks[0];
            new Philosopher4("Философ №" + (i + 1), leftFork, rightFork).start();
        }
    }
}
