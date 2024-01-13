package org.example;

import org.example.ver1.Fork;
import org.example.ver1.Philosopher;

import java.util.concurrent.Semaphore;

/**
 * Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит тарелка спагетти.
 * Вилки лежат на столе между каждой парой ближайших философов.
 * Каждый философ может либо есть, либо размышлять.
 * Философ может есть только тогда, когда держит две вилки — взятую справа и слева.
 * Философ не может есть два раза подряд, не прервавшись на размышления (можно не учитывать)
 * Описать в виде кода такую ситуацию. Каждый философ должен поесть три раза
 */
public class MainVer1 {
    // Первый вариант решения с помощью семафора, допускающего ограниченное число обедающих одновременно
    // НО: В первом варианте плохо то, что симафором могут завладеть два соседа, но кушать они будут по очереди,
    // в то время как другие могли бы есть одновременно.
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