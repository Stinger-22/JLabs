package com.labs.three.droid;

import static com.labs.three.util.Math.randomNumber;

public class DroidMelting extends CommonDroid {
    private int additionalDamage = 1;

    public DroidMelting(String name, int healthMax, int damageMin, int damageMax, int armor) {
        super(name, healthMax, damageMin, damageMax, armor);
    }

    public DroidMelting(DroidMelting droid) {
        super(droid.getName(), droid.getHealthMax(), droid.getDamageMin(), droid.getDamageMax(), droid.getArmor());
        this.additionalDamage = droid.getAdditionalDamage();
    }

    public int getAdditionalDamage() {
        return additionalDamage;
    }

    public void setAdditionalDamage(int additionalDamage) {
        this.additionalDamage = additionalDamage;
    }

    @Override
    public int getTotalDamage() {
        healthCurrent -= 5;
        additionalDamage +=  3;
        return randomNumber(damageMin, damageMax) + additionalDamage;
    }

    @Override
    public String toString() {
        return "DroidMelting{ health: " + healthCurrent + "/" + healthMax +
                ", damage: " + damageMin + "-" + damageMax + " + " + additionalDamage + ", armor=" + armor +
                ", name='" + name + '\'' + "}";
    }
}
