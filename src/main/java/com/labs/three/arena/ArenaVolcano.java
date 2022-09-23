package com.labs.three.arena;

import com.labs.three.droid.CommonDroid;

public class ArenaVolcano extends Arena {
    private final int armorModifier;

    public ArenaVolcano(int armorModifier) {
        this.armorModifier = armorModifier;
    }

    @Override
    protected void permanentEffect(CommonDroid droid) {
        droid.setArmor(droid.getArmor() + armorModifier);
    }
}
