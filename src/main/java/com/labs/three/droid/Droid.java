package com.labs.three.droid;

/**
 * Abstract class for droid which has only name
 */
public abstract class Droid {
    protected String name;

    /**
     * Constructor to set name of droid
     * @param name name of droid
     */
    public Droid(String name) {
        this.name = name;
    }

    /**
     * Get current name of droid
     * @return current name of droid
     */
    public String getName() {
        return name;
    }

    /**
     * Set new name for droid
     * @param name new name for droid
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method which is used when droid defends to decrease or increase incoming damage due to some effects, abilities or armor.
     * @param incomingDamage incoming damage to droid
     * @return how much hp droid lost
     */
    public abstract int defend(int incomingDamage);

    /**
     * Method which is used when droid attacks. It increases min-max damage if there is any special abilities, effects.
     * @return total damage
     */
    public abstract int getTotalDamage();
}