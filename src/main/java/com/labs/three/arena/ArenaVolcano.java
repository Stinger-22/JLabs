package com.labs.three.arena;

import com.labs.three.droid.CommonDroid;

/**
 * Arena which applies armor debuff on every droid
 */
public class ArenaVolcano extends Arena {
    private final int armorModifier;

    /**
     * Constructor of ArenaVolcano
     * @param armorModifier armor decrease of every droid
     */
    public ArenaVolcano(int armorModifier) {
        this.armorModifier = armorModifier;
    }

    @Override
    protected void permanentEffect(CommonDroid droid) {
        droid.setArmor(droid.getArmor() + armorModifier);
    }
}
