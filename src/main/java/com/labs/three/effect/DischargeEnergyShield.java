package com.labs.three.effect;

import com.labs.three.droid.CommonDroid;
import com.labs.three.droid.DroidEnergyShield;
import com.labs.three.droid.DroidTeam;

public class DischargeEnergyShield implements Effect{
    int dischargeAmount;

    public DischargeEnergyShield(int dischargeAmount) {
        this.dischargeAmount = dischargeAmount;
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
        if (droid instanceof DroidEnergyShield) {
            DroidEnergyShield droidES = (DroidEnergyShield) droid;
            int discharge = droidES.getEnergyShield() - dischargeAmount;
            if (discharge < 0) {
                discharge = 0;
            }
            droidES.setEnergyShield(discharge);
        }
    }

    @Override
    public EffectType type() {
        return EffectType.ON_ATTACK_TO_DEFENDER;
    }

    @Override
    public String toString() {
        return "DischargeEnergyShield{" + dischargeAmount + '}';
    }
}
