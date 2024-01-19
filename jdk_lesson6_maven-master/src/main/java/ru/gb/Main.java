package ru.gb;

import java.io.IOException;
import java.io.InputStream;

public class Main {
    void testReadResource() {
        try (InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("test.txt")) {
            if (resourceAsStream != null) {
                System.out.println(new String(resourceAsStream.readAllBytes()));
            } else {
                System.out.println("Ресурс не найден");
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main().testReadResource();
    }
}