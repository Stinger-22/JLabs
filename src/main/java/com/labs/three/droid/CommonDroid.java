package com.labs.three.droid;

import com.labs.three.effect.Affectable;
import com.labs.three.effect.Effect;

import static com.labs.three.util.Math.randomNumber;

/**
 * Class represent basic droids with no special behaviour. Has health, damage, armor. Can have some effect.
 */
public class CommonDroid extends Droid implements Affectable {
    protected int healthCurrent;
    protected int healthMax;
    protected int damageMin;
    protected int damageMax;
    protected int armor;

    private Effect effect;

    /**
     * Constructor for droid
     * @param name name of droid
     * @param healthMax max health of droid
     * @param damageMin min damage of droid
     * @param damageMax max damage of droid
     * @param armor armor of droid
     */
    public CommonDroid(String name, int healthMax, int damageMin, int damageMax, int armor) {
        super(name);
        this.healthCurrent = healthMax;
        this.healthMax = healthMax;
        this.damageMin = damageMin;
        this.damageMax = damageMax;
        this.armor = armor;
    }

    /**
     * Get current health of droid
     * @return current health of droid
     */
    public int getHealthCurrent() {
        return healthCurrent;
    }

    /**
     * Set current health of droid
     * @param healthCurrent new current health of droid
     */
    public void setHealthCurrent(int healthCurrent) {
        this.healthCurrent = healthCurrent;
    }

    /**
     * Get max health of droid
     * @return max health of droid
     */
    public int getHealthMax() {
        return healthMax;
    }

    /**
     * Set max health of droid
     * @param healthMax new max health of droid
     */
    public void setHealthMax(int healthMax) {
        this.healthMax = healthMax;
    }

    /**
     * Get min damage of droid
     * @return min damage of droid
     */
    public int getDamageMin() {
        return damageMin;
    }

    /**
     * Set min damage of droid
     * @param damageMin new min damage of droid
     */
    public void setDamageMin(int damageMin) {
        this.damageMin = damageMin;
    }

    /**
     * Get max damage of droid
     * @return max damage of droid
     */
    public int getDamageMax() {
        return damageMax;
    }

    /**
     * Set max damage of droid
     * @param damageMax max damage of droid
     */
    public void setDamageMax(int damageMax) {
        this.damageMax = damageMax;
    }

    /**
     * Get armor of droid
     * @return armor of droid
     */
    public int getArmor() {
        return armor;
    }

    /**
     * Set new armor for droid
     * @param armor new armor value for droid
     */
    public void setArmor(int armor) {
        this.armor = armor;
    }

    /**
     * Get effect of droid
     * @return effect of droid
     */
    public Effect getEffect() {
        return effect;
    }

    /**
     * Set new effect of droid
     * @param effect new effect of droid
     */
    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    /**
     * This method creates new instance of CommonDroid with the same characteristics
     * @return CommonDroid with the same characteristics
     */
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

    /**
     * Check if droid is alive
     * @return true if alive; false if dead
     */
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
