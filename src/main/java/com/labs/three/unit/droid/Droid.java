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

    public void attack(Unit target) {
        int damage = getTotalDamage();
        target.doDefend(damage);
    }

    public abstract void doDefend(int incomingDamage);
    public abstract int getTotalDamage();
}
