package org.example.animals;

public class Cat extends Animal {

    public Cat(String name) {
        super.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void getVoice() {
        System.out.println("Мяу");
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                '}';
    }
}
