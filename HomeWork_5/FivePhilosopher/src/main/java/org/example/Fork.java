package org.example;

public class Fork {
    private volatile boolean taken;
    private final String name;

    public Fork(String name) {
        this.taken = false;
        this.name = name;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public boolean isTaken() {
        return taken;
    }

    public String getName() {
        return name;
    }
}
