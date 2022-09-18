package com.labs.three.arena;

import com.labs.three.unit.droid.Droid;
import com.labs.three.unit.droid.DroidTeam;
import com.labs.three.util.ListCircularIterator;

public abstract class Arena {
    protected final DroidTeam team1;
    protected final DroidTeam team2;

    // TODO: deep copy
    public Arena(Droid droid1, Droid droid2) {
        this.team1 = new DroidTeam(droid1);
        this.team2 = new DroidTeam(droid2);
    }

    // TODO: deep copy
    public Arena(DroidTeam team1, DroidTeam team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    protected void punch(ListCircularIterator<Droid> attackerTeam, DroidTeam defenderTeam) {
        int damage, hit;
        Droid attacker, defender;

        attacker = chooseAttacker(attackerTeam);
        defender = chooseDefender(defenderTeam);

        damage = attacker.getTotalDamage();
        hit = defender.defend(damage);
        if (!defender.isAlive()) {
            defenderTeam.remove(defender);
        }

        printPunchInfo(attacker, defender, damage, hit);
    }

    // TODO: handle if droids have zero attack
    public DroidTeam fight() {
        ListCircularIterator<Droid> iteratorTeam1, iteratorTeam2;
        do {
            iteratorTeam1 = team1.iterator();
            if (iteratorTeam1.hasNext()) {
                punch(iteratorTeam1, team2);
            }
            iteratorTeam2 = team2.iterator();
            if (iteratorTeam2.hasNext()) {
                punch(iteratorTeam2, team1);
            }
        } while (team1.isAlive() && team2.isAlive());
        if (team1.isAlive()) {
            return team1;
        }
        else if (team2.isAlive()) {
            return team2;
        }
        else return null;
    }

    protected abstract Droid chooseAttacker(ListCircularIterator<Droid> iterator);
    protected abstract Droid chooseDefender(DroidTeam droidTeam);

    private void printPunchInfo(Droid attacker, Droid defender, int damage, int hit) {
        System.out.println("Attacker: " + attacker);
        System.out.println("Attacker damage: " + damage);
        System.out.println("Defender got hit by: " + hit + " damage");
        System.out.println("Defender: " + defender + "\n\n");
    }
}
