package com.labs.three.unit.droid;

import static com.labs.three.util.Math.randomNumber;

public class CommonDroid extends Droid {
    private int healthCurrent;
    private int healthMax;
    private int damageMin;
    private int damageMax;
    private int armor;

//    private List<Effect> effects;
//    private List<Effect> abilities;


    public CommonDroid(String name, int healthMax, int damageMin, int damageMax, int armor) {
        super(name);
        this.healthCurrent = healthMax;
        this.healthMax = healthMax;
        this.damageMin = damageMin;
        this.damageMax = damageMax;
        this.armor = armor;

//        this.effects = new ArrayList<>();
//        this.abilities = new ArrayList<>();
    }

    public int getHealthCurrent() {
        return healthCurrent;
    }

    public void setHealthCurrent(int healthCurrent) {
        this.healthCurrent = healthCurrent;
    }

    public int getHealthMax() {
        return healthMax;
    }

    public void setHealthMax(int healthMax) {
        this.healthMax = healthMax;
    }

    public int getDamageMin() {
        return damageMin;
    }

    public void setDamageMin(int damageMin) {
        this.damageMin = damageMin;
    }

    public int getDamageMax() {
        return damageMax;
    }

    public void setDamageMax(int damageMax) {
        this.damageMax = damageMax;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    @Override
    public String toString() {
        return "CommonDroid{ health: " + healthCurrent + "/" + healthMax +
                ", damage: " + damageMin + "-" + damageMax + ", armor=" + armor +
                ", name='" + name + '\'' + "}";
    }

    @Override
    public boolean isAlive() {
        return healthCurrent > 0;
    }

    @Override
    public int defend(int damage) {
        int gotDamaged = (damage > armor ? damage - armor : 0 );
        healthCurrent -= (damage > armor ? damage - armor : 0 );
        return gotDamaged;
    }

    @Override
    public int getTotalDamage() {
        return randomNumber(damageMin, damageMax);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommonDroid that = (CommonDroid) o;
        return healthCurrent == that.healthCurrent && healthMax == that.healthMax && damageMin == that.damageMin &&
               damageMax == that.damageMax && armor == that.armor && name.equals(that.name);
    }
}
