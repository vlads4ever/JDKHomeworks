package org.example;

/**
 * Напишите обобщенный класс Pair, который представляет собой пару значений разного типа. Класс должен иметь методы
 * getFirst(), getSecond() для получения значений каждого из составляющих пары, а также переопределение метода
 * toString(), возвращающее строковое представление пары.
 * @param <V1>
 * @param <V2>
 */
public class Pair <V1,V2> {
    private V1 first;
    private V2 second;

    public Pair(V1 key, V2 value) {
        this.first = key;
        this.second = value;
    }

    public V1 getFirst() {
        return first;
    }
    public V2 getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first.toString() +
                ", second=" + second.toString() +
                '}';
    }
}
