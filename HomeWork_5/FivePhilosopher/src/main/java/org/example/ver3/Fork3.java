package org.example.ver3;

public class Fork3 {
    private boolean taken;
    private final String name;

    public Fork3(String name) {
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
