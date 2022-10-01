package com.labs.three.effect;

import com.labs.three.droid.CommonDroid;
import com.labs.three.droid.DroidTeam;

public class RepairTeam implements Effect{
    int repairHP;

    public RepairTeam(int repairHP) {
        this.repairHP = repairHP;
    }

    @Override
    public void apply(DroidTeam droidTeam) {
        CommonDroid droid;
        for (int i = 0; i < droidTeam.size(); i++) {
            droid = droidTeam.get(i);
            apply(droid);
        }
    }

    @Override
    public void apply(CommonDroid droid) {
        droid.setHealthCurrent(droid.getHealthCurrent() + repairHP);
    }

    @Override
    public EffectType type() {
        return EffectType.ON_ATTACK_TO_DEFEND_TEAM;
    }

    @Override
    public String toString() {
        return "RepairTeam{" + repairHP + '}';
    }
}
