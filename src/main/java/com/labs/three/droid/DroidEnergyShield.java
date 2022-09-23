package com.labs.three.droid;

public class DroidEnergyShield extends CommonDroid {
    private int energyShield;

    public DroidEnergyShield(String name, int healthMax, int damageMin, int damageMax, int armor, int energyShield) {
        super(name, healthMax, damageMin, damageMax, armor);
        this.energyShield = energyShield;
    }

    public DroidEnergyShield(DroidEnergyShield droid) {
        super(droid.getName(), droid.getHealthMax(), droid.getDamageMin(), droid.getDamageMax(), droid.getArmor());
        this.energyShield = droid.getEnergyShield();
    }

    public int getEnergyShield() {
        return energyShield;
    }

    public void setEnergyShield(int energyShield) {
        this.energyShield = energyShield;
    }

    public DroidEnergyShield copy() {
        return new DroidEnergyShield(name, healthMax, damageMin, damageMax, armor, energyShield);
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
