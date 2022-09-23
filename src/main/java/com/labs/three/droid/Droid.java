package com.labs.three.droid;

public abstract class Droid {
    protected String name;

    public Droid(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract int defend(int incomingDamage);
    public abstract int getTotalDamage();
}