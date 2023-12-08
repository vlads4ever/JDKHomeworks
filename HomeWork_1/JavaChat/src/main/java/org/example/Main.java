package org.example;

public class Main {
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow(0, 0);
        new ChatWindow(serverWindow, 500, 0);
        new ChatWindow(serverWindow, 1000, 0);
    }
}