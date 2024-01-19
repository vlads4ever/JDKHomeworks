package ru.gb;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.util.ArithmeticUtils;

/*
В рамках выполнения задачи необходимо:
Создать свой Java Maven проект;
Добавьте зависимость commons-math3 (предназначена для решения математических задач)
и изучить интерфейс библиотеки.
Воспользоваться пакетом org.apache.commons.math3.stat и классом DescriptiveStatistics.
Создать коллекцию из числовых элементов.
С помощью объекта DescriptiveStatistics вычислить минимальное и максимальное значение,
сумму и среднее арифметическое.
Воспользоваться пакетом org.apache.commons.math3.util. С помощью класса ArithmeticUtils найти :
факториал числа N.
Наименьшее общее частное двух чисел
Наибольший общий делитель двух чисел
Проверить что число N это степень двойки
 */
public class Calculator {
    public static void main(String[] args) {
        DescriptiveStatistics statistics = new DescriptiveStatistics();
        statistics.addValue(1);
        statistics.addValue(2);
        statistics.addValue(3);

        System.out.println("Минимальное: " + statistics.getMin());
        System.out.println("Максимальное: " + statistics.getMax());
        System.out.println("Среднее арифметическое: " + statistics.getMean());

        int n = 5;
        System.out.println("Факториал числа " + n + " равен " + ArithmeticUtils.factorial(n));
        int a = 16;
        int b = 24;
        System.out.printf("Наименьшее общее частное чисел %s и %s равно %d\n", a, b,
                ArithmeticUtils.lcm(a, b));
        System.out.printf("Наибольший общий делитель чисел %s и %s равно %d\n", a, b,
                ArithmeticUtils.gcd(a, b));
        if (ArithmeticUtils.isPowerOfTwo(a)){
            System.out.printf("%d является степенью числа 2\n", a);
        } else {
            System.out.printf("%d не является степенью числа 2\n", a);
        }
    }
}
