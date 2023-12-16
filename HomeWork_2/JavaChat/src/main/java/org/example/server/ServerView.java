package org.example.server;

public interface ServerView {
    void stopServer();
    void startServer();
    void printInLog(String text);
}
