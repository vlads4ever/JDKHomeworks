package org.example;

/**
 * Создать справочник сотрудников
 * Необходимо:
 * Создать класс справочник сотрудников, который содержит внутри
 * коллекцию сотрудников - каждый сотрудник должен иметь следующие атрибуты:
 * Табельный номер
 * Номер телефона
 * Имя
 * Стаж
 * Добавить метод, который ищет сотрудника по стажу (может быть список)
 * Добавить метод, который возвращает номер телефона сотрудника по имени (может быть список)
 * Добавить метод, который ищет сотрудника по табельному номеру
 * Добавить метод добавление нового сотрудника в справочник
 */
public class Main {
    public static void main(String[] args) {
        EmployeeDirectory<Employee> employeeDirectory = new EmployeeDirectory<>();
        System.out.println(employeeDirectory.addNewEmployee(
                "Василий", 1564, 89114567843L, 3));
        System.out.println(employeeDirectory.addNewEmployee(
                "Антонина", 2564, 89114457893L, 5));
        System.out.println(employeeDirectory.addNewEmployee(
                "Константин", 4793, 89117694432L, 5));
        System.out.println(employeeDirectory.addNewEmployee(
                "Павел", 5935, 89114869954L, 13));
        System.out.println(employeeDirectory.addNewEmployee(
                "Зинаида", 2096, 89113245443L, 15));
        System.out.println();

        System.out.println("Поиск сотрудника по табельному номеру:");
        employeeDirectory.getEmployeeByPersonalNumber(2564).forEach(System.out::println);
        System.out.println();

        System.out.println("Поиск сотрудников по стажу:");
        employeeDirectory.getEmployeeByExperience(5).forEach(System.out::println);
        System.out.println();

        System.out.println("Запрос телефона по имени сотрудника:");
        employeeDirectory.getPhoneNumberByName("Антонина").forEach(Employee::printPhoneNumber);
    }

}