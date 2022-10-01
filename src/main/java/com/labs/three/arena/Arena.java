package com.labs.three.arena;

import com.labs.three.droid.CommonDroid;
import com.labs.three.droid.DroidTeam;
import com.labs.three.effect.Effect;
import com.labs.three.effect.EffectType;

import java.io.FileNotFoundException;

import static com.labs.three.util.Math.randomNumber;

public abstract class Arena implements IArena {
    private DroidTeam team1;
    private DroidTeam team2;

    private int iAttackerTeam1 = 0;
    private int iAttackerTeam2 = 0;

    Effect droidEffect;

    private static final ArenaView view = new ArenaView();

    public void setTeam1(DroidTeam team1) {
        this.team1 = team1;
        applyPermanentEffect(team1);
    }

    public void setTeam2(DroidTeam team2) {
        this.team2 = team2;
        applyPermanentEffect(team2);
    }

    protected int punch(DroidTeam attackerTeam, int iAttacker, DroidTeam defenderTeam) {
        int died = 0;

        int damage, hit;
        CommonDroid attacker, defender;
        attacker = chooseAttacker(attackerTeam, iAttacker);
        defender = chooseDefender(defenderTeam);

        damage = attacker.getTotalDamage();

        droidEffect = attacker.getEffect();
        if (droidEffect != null) {
            switch (droidEffect.type()) {
                case ON_ATTACK_TO_DEFENDER:
                    defender.applyEffect(droidEffect);
                    break;
                case ON_ATTACK_TO_ATTACKER:
                    attacker.applyEffect(droidEffect);
                    break;
//                case ON_ATTACK_TO_DEFEND_TEAM:
//                    defenderTeam.applyEffect(droidEffect);
//                    break;
//                case ON_ATTACK_TO_ATTACK_TEAM:
//                    attackerTeam.applyEffect(droidEffect);
//                    break;
//                case ON_ATTACK_TO_ALL:
//                    attackerTeam.applyEffect(droidEffect);
//                    defenderTeam.applyEffect(droidEffect);
            }
        }

        hit = defender.defend(damage);

        droidEffect = defender.getEffect();
        if (droidEffect != null) {
            switch (droidEffect.type()) {
                case ON_DEFEND_TO_DEFENDER:
                    defender.applyEffect(droidEffect);
                    break;
                case ON_DEFEND_TO_ATTACKER:
                    attacker.applyEffect(droidEffect);
                    break;
//                case ON_DEFEND_TO_DEFEND_TEAM:
//                    defenderTeam.applyEffect(droidEffect);
//                    break;
//                case ON_DEFEND_TO_ATTACK_TEAM:
//                    attackerTeam.applyEffect(droidEffect);
//                    break;
//                case ON_DEFEND_TO_ALL:
//                    attackerTeam.applyEffect(droidEffect);
//                    defenderTeam.applyEffect(droidEffect);
            }
        }

        if (!attacker.isAlive()) {
            attackerTeam.removeDroid(attacker);
            died ^= 1;

            droidEffect = attacker.getEffect();
//            if (droidEffect.type() == EffectType.ON_DEATH) {
//
//            }
        }
        if (!defender.isAlive()) {
            defenderTeam.removeDroid(defender);
            died ^= 2;
        }

        view.printPunchInfo(attacker, defender, damage, hit, attacker.getEffect());
        view.loadToStringBuilder(attacker, defender, damage, hit);
        return died;
    }

    // TODO: handle if droids have zero attack
    public DroidTeam fight() {
        view.initStringBuilder(team1, team2);

        if (team1 == null || team2 == null) {
            throw new IllegalStateException("Arena must have two teams.");
        }

        int died;
        while (true) {
            if (team1.isAlive() && team2.isAlive()) {
                died = punch(team1, iAttackerTeam1, team2);
                if ((died & 1) == 1) {
                    iAttackerTeam1++;
                }
                if ((died & 2) == 2) {
                    iAttackerTeam2--;
                }
                if (iAttackerTeam1 >= team1.size()) {
                    iAttackerTeam1 = 0;
                }
                if (iAttackerTeam2 < 0) {
                    iAttackerTeam2 = 0;
                }
            }
            if (team1.isAlive() && team2.isAlive()) {
                died = punch(team2, iAttackerTeam2, team1);
                if ((died & 1) == 1) {
                    iAttackerTeam2++;
                }
                if ((died & 2) == 2) {
                    iAttackerTeam1--;
                }
                if (iAttackerTeam2 >= team2.size()) {
                    iAttackerTeam2 = 0;
                }
                if (iAttackerTeam1 < 0) {
                    iAttackerTeam1 = 0;
                }
            }
            else {
                break;
            }
        }

        if (team1.isAlive()) {
            view.saveFightEnd(team1);
            return team1;
        }
        else if (team2.isAlive()) {
            view.saveFightEnd(team2);
            return team2;
        }
        else {
            view.saveFightEnd(null);
            return null;
        }
    }

    private CommonDroid chooseAttacker(DroidTeam droidTeam, int iAttacker) {
        return droidTeam.get(iAttacker);
    }

    private CommonDroid chooseDefender(DroidTeam droidTeam) {
        int random = randomNumber(0, droidTeam.size() - 1);
        return droidTeam.get(random);
    }

    protected abstract void permanentEffect(CommonDroid droid);

    private void applyPermanentEffect(DroidTeam droidTeam) {
        for (int i = 0; i < droidTeam.size(); i++) {
            permanentEffect(droidTeam.get(i));
        }
    }

    public static void saveLastFight(String path) throws FileNotFoundException {
        view.saveFightToFile(path);
    }
}
