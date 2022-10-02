package com.labs.three.droid;

import static com.labs.three.util.Math.randomNumber;

/**
 * Droid which stack additional damage when he attacks but hurts himself
 */
public class DroidMelting extends CommonDroid {
    private int healthHurt;
    private int additionalDamage;
    private int totalAdditionalDamage = 0;

    /**
     * Constructor for DroidMelting
     * @param name name of droid
     * @param healthMax max health of droid
     * @param damageMin min damage of droid
     * @param damageMax max damage of droid
     * @param armor armor of droid
     * @param healthHurt how many hp droid lose on every attack
     * @param additionalDamage how much additional damage he gains with every attack
     */
    public DroidMelting(String name, int healthMax, int damageMin, int damageMax, int armor, int healthHurt, int additionalDamage) {
        super(name, healthMax, damageMin, damageMax, armor);
        this.healthHurt = healthHurt;
        this.additionalDamage = additionalDamage;
    }

    /**
     * Get amount af additional damage he gains with every attack
     * @return amount af additional damage he gains with every attack
     */
    public int getAdditionalDamage() {
        return additionalDamage;
    }

    /**
     * Set new amount of damage he gains with every attack
     * @param additionalDamage amount af additional damage he gains with every attack
     */
    public void setAdditionalDamage(int additionalDamage) {
        this.additionalDamage = additionalDamage;
    }

    /**
     * Get health amount droid lose on every attack
     * @return health amount droid lose on every attack
     */
    public int getHealthHurt() {
        return healthHurt;
    }

    /**
     * Set health amount which droid lose on every attack
     * @param healthHurt new health amount droid lose on every attack
     */
    public void setHealthHurt(int healthHurt) {
        this.healthHurt = healthHurt;
    }

    /**
     * Get total additional damage which droid acquired with his attacks
     * @return total additional damage
     */
    public int getTotalAdditionalDamage() {
        return totalAdditionalDamage;
    }

    /**
     * Set total additional damage which droid acquired with his attacks
     * @param totalAdditionalDamage new additional damage he gains with every attack
     */
    public void setTotalAdditionalDamage(int totalAdditionalDamage) {
        this.totalAdditionalDamage = totalAdditionalDamage;
    }

    /**
     * This method creates new instance of DroidMelting with the same characteristics
     * @return DroidMelting with the same characteristics
     */
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
