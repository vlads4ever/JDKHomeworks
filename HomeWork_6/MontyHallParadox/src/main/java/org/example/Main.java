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
    private static final int NUMBER_OF_GAMES = 1000;

    public static void main(String[] args) {
        Map<Integer, Boolean> resultsChange = gamesWithChange();
        int winsChange = (int) resultsChange.values().stream().filter(b -> b).count();
        int defeatsChange = resultsChange.size() - winsChange;
        double winsChangePercent = (double) winsChange / NUMBER_OF_GAMES * 100;
        System.out.println("Статистика побед игрока при изменении первоначального выбора:");
        System.out.printf("Побед: %d Поражений: %d Процент побед: %.1f%%\n",
                winsChange, defeatsChange, winsChangePercent);

        Map<Integer, Boolean> resultsNotChange = gamesWithoutChange();
        int winsNotChange = (int) resultsNotChange.values().stream().filter(b -> b).count();
        int defeatsNotChange = resultsNotChange.size() - winsNotChange;
        double winsNotChangePercent = (double) winsNotChange / NUMBER_OF_GAMES * 100;
        System.out.println("Статистика побед игрока при неизменности первоначального выбора:");
        System.out.printf("Побед: %d Поражений: %d Процент побед: %.1f%%\n",
                winsNotChange, defeatsNotChange, winsNotChangePercent);
    }

    /**
     * Подготовить игровой сет
     * @return Игровой сет
     */
    private static boolean[] getArray() {
        boolean[] array = new boolean[3];
        int randomIndex = random.nextInt(0,3);
        array[randomIndex] = true;
        return array;
    }

    /**
     * Открыть для подсказки дверь, отличную от выбора игрока и не выигрышную
     * @param firstChoice Выбор игрока
     * @param array Игровой сет
     * @return
     */
    private static int openTheDoor(int firstChoice, boolean[] array) {
        int openDoor = 0;
        for (int i = 0; i < 3; i++) {
            if (i != firstChoice && !array[i]) openDoor = i;
        }
        return openDoor;
    }

    /**
     * Второй ход игрока, отличный от первого и не открытая дверь
     * @param firstChoice Первый ход игрока
     * @param openDoor Открытая дверь
     * @return
     */
    private static int getSecondChoice(int firstChoice, int openDoor) {
        int secondChoice = 0;
        for (int i = 0; i < 3; i++) {
            if (i != firstChoice && i != openDoor) secondChoice = i;
        }
        return secondChoice;
    }

    /**
     * Эмуляция 1000 циклов игры, где игрок меняет первоначальный выбор
     * @return Map<Integer, Boolean> resultsChange
     */
    private static Map<Integer, Boolean> gamesWithChange() {
        Map<Integer, Boolean> resultsChange = new HashMap<>();
        for (int i = 0; i < NUMBER_OF_GAMES; i++) {
            boolean[] array = getArray();
            int firstChoice = random.nextInt(0,3);
            int openDoor = openTheDoor(firstChoice, array);
            int secondChoice = getSecondChoice(firstChoice, openDoor);
            resultsChange.put(i, array[secondChoice]);
        }
        return resultsChange;
    }

    /**
     * Эмуляция 1000 циклов игры, где игрок НЕ меняет первоначальный выбор
     * @return Map<Integer, Boolean> resultsNotChange
     */
    private static Map<Integer, Boolean> gamesWithoutChange() {
        Map<Integer, Boolean> resultsNotChange = new HashMap<>();
        for (int i = 0; i < NUMBER_OF_GAMES; i++) {
            boolean[] array = getArray();
            int firstChoice = random.nextInt(0,3);
            resultsNotChange.put(i, array[firstChoice]);
        }
        return resultsNotChange;
    }
}