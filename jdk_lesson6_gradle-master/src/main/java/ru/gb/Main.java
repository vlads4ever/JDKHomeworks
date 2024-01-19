package ru.gb;

/*
В рамках выполнения задачи необходимо:
Создать свой Java Gradle проект;
Добавить зависимость Guava (популярная библиотека от Google, содержащая набор утилитарных механизмов).
Воспользоваться утилитарным классом Lists:
Развернуть список элементов
Получить лист Character из строки
Разделить лист на группы по 2 элемента
Воспользоваться утилитарным классом Sets
Получить итоговый Set из двух коллекций
Получить итоговый Set из общих элементов двух коллекций
Получить итоговый Set из непересекающихся элементов двух коллекций
 */

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        System.out.println(list);
        System.out.println(Lists.reverse(list));
        System.out.println(Lists.partition(list, 2));

        String text = "strings";
        System.out.println(Lists.charactersOf(text));

        Set<String> set1 = new HashSet<>(Arrays.asList("1", "2", "3"));
        Set<String> set2 = new HashSet<>(Arrays.asList("1", "b", "c"));
        System.out.println(Sets.union(set1, set2));
        System.out.println(Sets.intersection(set1, set2));
        System.out.println(Sets.symmetricDifference(set1, set2));
    }
}