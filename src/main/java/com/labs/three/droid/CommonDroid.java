package com.labs.three.droid;

import com.labs.three.effect.Affectable;
import com.labs.three.effect.Effect;

import static com.labs.three.util.Math.randomNumber;

public class CommonDroid extends Droid implements Affectable {
    protected int healthCurrent;
    protected int healthMax;
    protected int damageMin;
    protected int damageMax;
    protected int armor;

    Effect effect;

    public CommonDroid(String name, int healthMax, int damageMin, int damageMax, int armor) {
        super(name);
        this.healthCurrent = healthMax;
        this.healthMax = healthMax;
        this.damageMin = damageMin;
        this.damageMax = damageMax;
        this.armor = armor;
    }

    public CommonDroid(CommonDroid droid) {
        super(droid.getName());
        this.healthCurrent = droid.getHealthMax();
        this.healthMax = droid.getHealthMax();
        this.damageMin = droid.getDamageMin();
        this.damageMax = droid.getHealthMax();
        this.armor = droid.getArmor();
        this.effect = droid.effect;
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

    public Effect getEffect() {
        return effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public CommonDroid copy() {
        CommonDroid droid = new CommonDroid(name, healthMax, damageMin, damageMax, armor);
        droid.setEffect(this.getEffect());
        return droid;
    }

    @Override
    public String toString() {
        return "CommonDroid{ health: " + healthCurrent + "/" + healthMax +
                ", damage: " + damageMin + "-" + damageMax + ", armor=" + armor +
                ", name='" + name + '\'' + "}";
    }

    public boolean isAlive() {
        return healthCurrent > 0;
    }

    @Override
    public int defend(int damage) {
        int gotDamaged = (damage > armor ? damage - armor : 0 );
        healthCurrent -= gotDamaged;
        return gotDamaged;
    }

    @Override
    public int getTotalDamage() {
        return randomNumber(damageMin, damageMax);
    }

    @Override
    public void applyEffect(Effect effect) {
        effect.apply(this);
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
