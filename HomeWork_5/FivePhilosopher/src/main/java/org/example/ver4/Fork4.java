package org.example.ver4;

import java.util.concurrent.atomic.AtomicBoolean;

public class Fork4 {
    private AtomicBoolean taken;
    private final String name;

    public Fork4(String name) {
        this.name = name;
        this.taken = new AtomicBoolean(false);
    }

    public void setTaken(boolean taken) {
        this.taken.set(taken);
    }

    public AtomicBoolean getTaken() {
        return taken;
    }

    public String getName() {
        return name;
    }
}
