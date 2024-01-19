package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * В качестве задачи предлагаю вам реализовать код для демонстрации парадокса Монти Холла и
 * наглядно убедиться в верности парадокса (запустить игру в цикле на 1000 и вывести итоговый счет).
 * Необходимо:
 * Создать свой Java Maven или Gradle проект;
 * Самостоятельно реализовать прикладную задачу;
 * Сохранить результат в HashMap<шаг теста, результат>
 * Вывести на экран статистику по победам и поражениям
 */

public class Main {
    private static final Random random = new Random();

    public static void main(String[] args) {
        Map<Integer, Boolean> resultsChange = gamesWithChange();
        int winsChange = (int) resultsChange.values().stream().filter(b -> b).count();
        int defeatsChange = resultsChange.size() - winsChange;
        double winsChangePercent = (double) winsChange / 1000;
        System.out.println("Статистика побед игрока при изменении первоначального выбора:");
        System.out.printf("Побед: %d, Поражений: %d\n", winsChange, defeatsChange);
        System.out.printf("Процент побед: %.3f\n", winsChangePercent);

        Map<Integer, Boolean> resultsNotChange = gamesWithoutChange();
        int winsNotChange = (int) resultsNotChange.values().stream().filter(b -> b).count();
        int defeatsNotChange = resultsNotChange.size() - winsNotChange;
        double winsNotChangePercent = (double) winsNotChange / 1000;
        System.out.println("Статистика побед игрока при изменении первоначального выбора:");
        System.out.printf("Побед: %d, Поражений: %d\n", winsNotChange, defeatsNotChange);
        System.out.printf("Процент побед: %.3f\n", winsNotChangePercent);
    }

    public static boolean[] getArray(Random random) {
        boolean[] array = new boolean[3];
        int randomIndex = random.nextInt(0,3);
        array[randomIndex] = true;
        return array;
    }

    public static int getSecondChoice(int firstChoice, Random random) {
        while (true) {
            int secondChoice = random.nextInt(0, 3);
            if (secondChoice != firstChoice) {
                return secondChoice;
            }
        }
    }

    /**
     * Эмуляция 1000 циклов игры, где игрок меняет первоначальный выбор
     * @return Map<Integer, Boolean> resultsChange
     */
    public static Map<Integer, Boolean> gamesWithChange() {
        Map<Integer, Boolean> resultsChange = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            boolean[] array = getArray(random);
            int firstChoice = random.nextInt(0,3);
            if (array[firstChoice]) {
                resultsChange.put(i, true);
            } else {
                int secondChoice = getSecondChoice(firstChoice, random);
                if (array[secondChoice]) {
                    resultsChange.put(i, true);
                } else {
                    resultsChange.put(i, false);
                }
            }
        }
        return resultsChange;
    }

    /**
     * Эмуляция 1000 циклов игры, где игрок НЕ меняет первоначальный выбор
     * @return Map<Integer, Boolean> resultsNotChange
     */
    public static Map<Integer, Boolean> gamesWithoutChange() {
        Map<Integer, Boolean> resultsNotChange = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            boolean[] array = getArray(random);
            int firstChoice = random.nextInt(0,3);
            if (array[firstChoice]) {
                resultsNotChange.put(i, true);
            } else {
                resultsNotChange.put(i, false);
            }
        }
        return resultsNotChange;
    }
}