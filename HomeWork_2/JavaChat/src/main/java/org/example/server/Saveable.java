package org.example.server;

public interface Saveable {
    boolean saveLog(StringBuilder chat);
    String loadLog();
}
