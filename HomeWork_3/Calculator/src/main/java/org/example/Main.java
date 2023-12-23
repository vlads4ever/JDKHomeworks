package org.example;

import org.example.animals.Animal;
import org.example.animals.Cat;
import org.example.animals.Dog;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        System.out.println("Task 1");
        System.out.println("----------------------------------");
        int aInt = 5;
        int bInt = 3;
        double aDbl = 4.3;
        double bDbl = 7.8;
        float aFlt = 3.7f;
        float bFlt = 8.1f;
        long aLng = 8L;
        long bLng = 4L;

        System.out.println(
                "Сумма " + aInt + " + " + bInt + " = " + Calculator.sum(aInt, bInt) + "\n" +
                "Сумма " + aDbl + " + " + bInt + " = " + Calculator.sum(aDbl, bInt) + "\n" +
                "Сумма " + aInt + " + " + bFlt + " = " + Calculator.sum(aDbl, bInt) + "\n" +
                "Сумма " + aLng + " + " + bInt + " = " + Calculator.sum(aDbl, bInt) + "\n" +
                "Произведение " + aInt + " * " + bInt + " = " + Calculator.multiply(aInt, bInt) + "\n" +
                "Произведение " + aInt + " * " + bDbl + " = " + Calculator.multiply(aInt, bDbl) + "\n" +
                "Произведение " + aFlt + " * " + bInt + " = " + Calculator.multiply(aFlt, bInt) + "\n" +
                "Произведение " + aInt + " * " + bLng + " = " + Calculator.multiply(aInt, bLng) + "\n" +
                "Частное " + aInt + " / " + bInt + " = " + Calculator.divide(aInt, bInt) + "\n" +
                "Частное " + aInt + " / " + bDbl + " = " + Calculator.divide(aInt, bDbl) + "\n" +
                "Частное " + aFlt + " / " + bInt + " = " + Calculator.divide(aFlt, bInt) + "\n" +
                "Частное " + aInt + " / " + bLng + " = " + Calculator.divide(aInt, bLng) + "\n" +
                "Разность " + aInt + " - " + bInt + " = " + Calculator.subtract(aInt, bInt) + "\n" +
                "Разность " + aInt + " - " + bDbl + " = " + Calculator.subtract(aInt, bDbl) + "\n" +
                "Разность " + aFlt + " - " + bInt + " = " + Calculator.subtract(aFlt, bInt) + "\n" +
                "Разность " + aInt + " - " + bLng + " = " + Calculator.subtract(aInt, bLng) + "\n"
        );

        System.out.println("Task 2");
        System.out.println("----------------------------------");
        Cat cat1 = new Cat("Vasya");
        Cat cat2 = new Cat("Pusya");
        Cat cat3 = new Cat("Barsik");
        Animal animal1 = cat1;
        Animal animal2 = cat2;
        Animal animal3 = cat3;
        Animal animal4 = new Cat("Busya");;

        Cat[] arrayCats = {cat1, cat2, cat3};
        Animal[] arrayAnimals = {animal1, animal2, animal3};
        Animal[] arrayAnimals2 = {animal1, animal2, animal4};
        System.out.println(
                "Сравниваем массив: " + Arrays.toString(arrayCats) + "\n" +
                "и массив: " + Arrays.toString(arrayAnimals) + "\n" +
                "Результат: " + compareArrays(arrayCats, arrayAnimals) + "\n"
        );
        System.out.println(
                "Сравниваем массив: " + Arrays.toString(arrayCats) + "\n" +
                "и массив: " + Arrays.toString(arrayAnimals2) + "\n" +
                "Результат: " + compareArrays(arrayCats, arrayAnimals2) + "\n"
        );
        System.out.println();

        System.out.println("Task 3");
        System.out.println("----------------------------------");
        Pair<Cat, Dog> pair = new Pair<>(new Cat("Vasya"), new Dog("Tuzik"));
        System.out.println(
                "Объект пары: " + pair.toString() + "\n" +
                "Первый элемент: " + pair.getFirst().toString() + "\n" +
                "Второй элемент: " + pair.getSecond().toString()
        );
    }

    /**
     * Напишите обобщенный метод compareArrays(), который принимает два массива и возвращает true, если они одинаковые,
     * и false в противном случае. Массивы могут быть любого типа данных, но должны иметь одинаковую длину и содержать
     * элементы одного типа по парно.
     */
    public static <T1 extends Animal, T2 extends Animal> boolean compareArrays(T1[] array1, T2[] array2) {
        if (array1.length != array2.length)
            return false;
        for (int i = 0; i < array1.length; i++) {
            if (array1[i].compareTo(array2[i]) != 0)
                return false;
        }
        return true;
    }

}