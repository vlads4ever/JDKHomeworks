package org.example.animals;

public class Dog extends Animal {

    public Dog(String name) {
        super.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void getVoice() {
        System.out.println("Гав");
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }
}
