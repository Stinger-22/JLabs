package com.labs.three.droid;

/**
 * Droid with energy shield which defends him from incoming damage
 */
public class DroidEnergyShield extends CommonDroid {
    private int energyShield;

    /**
     * Constructor for droid with energy shield
     * @param name name of droid
     * @param healthMax max health of droid
     * @param damageMin min damage of droid
     * @param damageMax max damage of droid
     * @param armor armor of droid
     * @param energyShield amount of energy shield
     */
    public DroidEnergyShield(String name, int healthMax, int damageMin, int damageMax, int armor, int energyShield) {
        super(name, healthMax, damageMin, damageMax, armor);
        this.energyShield = energyShield;
    }

    /**
     * Get current amount of energy shield
     * @return amount of energy shield
     */
    public int getEnergyShield() {
        return energyShield;
    }

    /**
     * Set new amount of energy shield
     * @param energyShield new amount of energy shield
     */
    public void setEnergyShield(int energyShield) {
        this.energyShield = energyShield;
    }

    /**
     * This method creates new instance of DroidEnergyShield with the same characteristics
     * @return DroidEnergyShield with the same characteristics
     */
    public DroidEnergyShield copy() {
        DroidEnergyShield droid = new DroidEnergyShield(name, healthMax, damageMin, damageMax, armor, energyShield);
        droid.setEffect(this.getEffect());
        return droid;
    }

    @Override
    public int defend(int damage) {
        if (energyShield > 0) {
            energyShield -= damage;
            if (energyShield < 0) {
                energyShield = 0;
                return defend(-energyShield);
            }
            return 0;
        }
        else {
            int gotDamaged = (damage > armor ? damage - armor : 0 );
            healthCurrent -= gotDamaged;
            return gotDamaged;
        }
    }

    @Override
    public String toString() {
        return "DroidEnergyShield{ health: " + healthCurrent + "/" + healthMax +
                ", damage: " + damageMin + "-" + damageMax + ", armor=" + armor + ", energy shield = " + energyShield +
                ", name='" + name + '\'' + "}";
    }
}
