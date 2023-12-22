package org.example;

import org.example.animals.Animal;
import org.example.animals.Cat;

import java.sql.Array;

public class Main {
    public static void main(String[] args) {

//        System.out.println("Task 1");
//        int intResult = Calculator.sum(5, 10); // Вернет 15
//        double doubleResult = Calculator.sum(2.5, 3); // Вернет 5.5
//        float floatResult = Calculator.sum(1.5, 2.5); // Вернет 4.0
//        long longResult = Calculator.sum(100L, 200L); // Вернет 300
//
//        System.out.println(
//                intResult + "\n" +
//                doubleResult + "\n" +
//                floatResult + "\n" +
//                longResult
//        );

        System.out.println("Task 2");
        Cat[] array1 = {new Cat("Vasya"), new Cat("Petya"), new Cat("Barsik")};
        Animal[] array2 = {new Cat("Vasya"), new Cat("Petya"), new Cat("Barsik")};
        System.out.println(compareArrays(array1, array2));
    }

    /**
     * Напишите обобщенный метод compareArrays(), который принимает два массива и возвращает true, если они одинаковые,
     * и false в противном случае. Массивы могут быть любого типа данных, но должны иметь одинаковую длину и содержать
     * элементы одного типа по парно.
     */
    public static <T1, T2> boolean compareArrays(T1[] array1, T2[] array2) {
        if (array1.length != array2.length) return false;
        for (int i = 0; i < array1.length; i++) {
            if (array1[0] instanceof <? super T2>)
        }
        return true;
    }

}