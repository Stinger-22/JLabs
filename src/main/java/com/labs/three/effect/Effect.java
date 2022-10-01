package com.labs.three.effect;

import com.labs.three.droid.CommonDroid;
import com.labs.three.droid.DroidTeam;

public interface Effect {
    void apply(DroidTeam droidTeam);
    void apply(CommonDroid droid);
    EffectType type();
}
