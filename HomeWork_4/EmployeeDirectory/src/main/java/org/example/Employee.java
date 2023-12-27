package org.example;

/**
 * Создать класс справочник сотрудников, который содержит внутри
 * коллекцию сотрудников - каждый сотрудник должен иметь следующие атрибуты:
 * Табельный номер
 * Номер телефона
 * Имя
 * Стаж
 */
public class Employee {
    private String name;
    private int personalNumber;
    private long phoneNumber;
    private int experience;

    public Employee(String name, int personalNumber, long phoneNumber, int experience) {
        this.name = name;
        this.personalNumber = personalNumber;
        this.phoneNumber = phoneNumber;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public int getPersonalNumber() {
        return personalNumber;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public int getExperience() {
        return experience;
    }

    public void printPhoneNumber() {
        System.out.println(phoneNumber);
    }

    @Override
    public String toString() {
        return "Имя:'" + name + '\'' +
                ", Табельный номер:" + personalNumber +
                ", Телефонный номер:" + phoneNumber +
                ", Стаж (лет):" + experience;
    }
}
