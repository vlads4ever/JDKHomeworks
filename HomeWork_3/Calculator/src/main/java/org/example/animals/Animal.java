package org.example.animals;

public abstract class Animal implements Comparable<Animal> {
    public String name;
    public abstract void getVoice();

    @Override
    public int compareTo(Animal o) {
        return name.compareTo(((Animal) o).name);
    }
}
