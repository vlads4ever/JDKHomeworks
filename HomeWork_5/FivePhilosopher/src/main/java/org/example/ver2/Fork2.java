package org.example.ver2;

public class Fork2 {
    private volatile boolean taken;
    private final String name;

    public Fork2(String name) {
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
