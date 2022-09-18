package com.labs.three.arena;

import com.labs.three.unit.droid.Droid;
import com.labs.three.unit.droid.DroidTeam;
import com.labs.three.util.ListCircularIterator;

import static com.labs.three.util.Math.randomNumber;

public class ArenaClassic extends Arena {
    public ArenaClassic(Droid droid1, Droid droid2) {
        super(droid1, droid2);
    }

    public ArenaClassic(DroidTeam team1, DroidTeam team2) {
        super(team1, team2);
    }

    protected Droid chooseAttacker(ListCircularIterator<Droid> iterator) {
        return iterator.next();
    }

    protected Droid chooseDefender(DroidTeam droidTeam) {
        int random = randomNumber(0, droidTeam.size() - 1);
        return droidTeam.get(random);
    }
}
