package com.labs.three.droid;

import static com.labs.three.util.Math.randomNumber;

public class DroidMelting extends CommonDroid {
    private int healthHurt;
    private int additionalDamage;
    private int totalAdditionalDamage = 0;

    public DroidMelting(String name, int healthMax, int damageMin, int damageMax, int armor, int healthHurt, int additionalDamage) {
        super(name, healthMax, damageMin, damageMax, armor);
        this.healthHurt = healthHurt;
        this.additionalDamage = additionalDamage;
    }

    public DroidMelting(DroidMelting droid) {
        super(droid.getName(), droid.getHealthMax(), droid.getDamageMin(), droid.getDamageMax(), droid.getArmor());
        this.healthHurt = droid.getHealthHurt();
        this.additionalDamage = droid.getAdditionalDamage();
    }

    public int getAdditionalDamage() {
        return additionalDamage;
    }

    public void setAdditionalDamage(int additionalDamage) {
        this.additionalDamage = additionalDamage;
    }

    public int getHealthHurt() {
        return healthHurt;
    }

    public void setHealthHurt(int healthHurt) {
        this.healthHurt = healthHurt;
    }

    public int getTotalAdditionalDamage() {
        return totalAdditionalDamage;
    }

    public void setTotalAdditionalDamage(int totalAdditionalDamage) {
        this.totalAdditionalDamage = totalAdditionalDamage;
    }

    public DroidMelting copy() {
        DroidMelting droid = new DroidMelting(name, healthMax, damageMin, damageMax, armor, healthHurt, additionalDamage);
        droid.setEffect(this.getEffect());
        return droid;
    }

    @Override
    public int getTotalDamage() {
        healthCurrent -= healthHurt;
        totalAdditionalDamage += additionalDamage;
        return randomNumber(damageMin, damageMax) + totalAdditionalDamage;
    }

    @Override
    public String toString() {
        return "DroidMelting{ health: " + healthCurrent + "/" + healthMax +
                ", damage: " + damageMin + "-" + damageMax + " + " + totalAdditionalDamage + ", armor=" + armor +
                ", name='" + name + '\'' + "}";
    }
}
