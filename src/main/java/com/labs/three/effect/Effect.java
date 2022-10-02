package com.labs.three.effect;

import com.labs.three.droid.CommonDroid;
import com.labs.three.droid.DroidTeam;

/**
 * Interface which allows applying effects on DroidTeam or CommonDroid instances
 */
public interface Effect {
    /**
     * Apply effect on DroidTeam
     * @param droidTeam DroidTeam on which effect is applied
     */
    void apply(DroidTeam droidTeam);

    /**
     * Apply effect on CommonDroid
     * @param droid CommonDroid on which effect is applied
     */
    void apply(CommonDroid droid);

    EffectType type();
}
