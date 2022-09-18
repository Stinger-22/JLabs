package com.labs.three.unit.droid;

import com.labs.three.unit.Unit;

public abstract class Droid implements Unit {
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
