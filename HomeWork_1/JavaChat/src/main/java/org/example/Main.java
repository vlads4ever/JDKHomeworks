package org.example;

public class Main {
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow();
        new ChatWindow(serverWindow);
    }
}