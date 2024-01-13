package org.example;

import org.example.ver3.Fork3;
import org.example.ver3.Philosopher3;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит тарелка спагетти.
 * Вилки лежат на столе между каждой парой ближайших философов.
 * Каждый философ может либо есть, либо размышлять.
 * Философ может есть только тогда, когда держит две вилки — взятую справа и слева.
 * Философ не может есть два раза подряд, не прервавшись на размышления (можно не учитывать)
 * Описать в виде кода такую ситуацию. Каждый философ должен поесть три раза
 */
public class MainVer3 {
    // Третий вариант решения с помощью синхронизированной коллекции для определения занятости вилки
    // НО: В третьем варианте вроде бы еще меньше защиты использовано, чем во втором варианте и возможна такая же ситуация.
    private static final int philosophersNumber = 5;
    private static CopyOnWriteArrayList<Fork3> forks = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < philosophersNumber; i++) {
            forks.add(new Fork3("Вилка №" + (i + 1))) ;
        }

        for (int i = 0; i < philosophersNumber; i++) {
            Fork3 leftFork = forks.get(i);
            Fork3 rightFork = (i < philosophersNumber - 1) ? forks.get(i + 1) : forks.get(0);
            new Philosopher3("Философ №" + (i + 1), leftFork, rightFork).start();
        }
    }
}
