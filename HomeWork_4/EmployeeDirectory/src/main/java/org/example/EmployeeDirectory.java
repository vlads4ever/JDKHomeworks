package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Создать класс справочник сотрудников, который содержит внутри
 * коллекцию сотрудников.
 * Добавить метод, который ищет сотрудника по стажу (может быть список)
 * Добавить метод, который возвращает номер телефона сотрудника по имени (может быть список)
 * Добавить метод, который ищет сотрудника по табельному номеру
 * Добавить метод добавление нового сотрудника в справочник
 * @param <E>
 */
public class EmployeeDirectory <E extends Employee> {
    List<E> eDirectory;

    public EmployeeDirectory() {
        this.eDirectory = new ArrayList<>();
    }

    public List<E> getEmployeeByExperience(int experience) {
        return eDirectory.stream().filter(n -> n.getExperience() == experience).toList();
    }

    public List<E> getPhoneNumberByName(String name) {
        return eDirectory.stream().filter(n -> n.getName().equals(name)).toList();
    }

    public List<E> getEmployeeByPersonalNumber(int personalNumber) {
        return eDirectory.stream().filter(n -> n.getPersonalNumber() == personalNumber).toList();
    }

    public String addNewEmployee(String name, int personalNumber, long phoneNumber, int experience) {
        try {
            eDirectory.add((E) new Employee(name, personalNumber, phoneNumber, experience));
            return "Новый сотрудник добавлен в справочник.";
        } catch (Exception e) {
            return "Ошибка добавления нового сотрудника!";
        }
    }

}
