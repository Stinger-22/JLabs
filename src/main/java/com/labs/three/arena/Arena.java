package com.labs.three.arena;

import com.labs.three.arena.view.ArenaView;
import com.labs.three.arena.view.ArenaViewConsole;
import com.labs.three.droid.CommonDroid;
import com.labs.three.droid.DroidTeam;
import com.labs.three.effect.Effect;

import static com.labs.three.util.Math.randomNumber;

public abstract class Arena implements IArena {
    private DroidTeam team1;
    private DroidTeam team2;

    private int iAttackerTeam1 = 0;
    private int iAttackerTeam2 = 0;

    private Effect droidEffect;

    private static final ArenaView view = new ArenaViewConsole();

    public void setTeam1(DroidTeam team1) {
        this.team1 = team1;
        applyPermanentEffect(team1);
    }

    public void setTeam2(DroidTeam team2) {
        this.team2 = team2;
        applyPermanentEffect(team2);
    }

    /**
     * Handles attack of one droid to another, application of effects and droid death
     * @param attackerTeam team from which attacker is chosen
     * @param iAttacker index of attacker from attackerTeam. This parameter has to be due to sequential order of attackers
     * @param defenderTeam team from which defender is chosen
     * @return 0 - if no droids died; 1 - if attacker died; 2 - if defender died
     */
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

        view.showPunch(attacker, defender, damage, hit, attacker.getEffect());
        view.loadToOutputPunch(attacker, defender, damage, hit);
        return died;
    }

    // TODO: handle if droids have zero attack
    public DroidTeam fight() {
        view.initOutput();
        view.loadToOutputFightStart(team1, team2);

        if (team1 == null || team2 == null) {
            throw new IllegalStateException("Arena must have two teams.");
        }

        int died;
        while (true) {
            if (team1.isEmpty() && team2.isEmpty()) {
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
            if (team1.isEmpty() && team2.isEmpty()) {
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

        if (team1.isEmpty()) {
            view.loadToOutputWinner(team1);
            return team1;
        }
        else if (team2.isEmpty()) {
            view.loadToOutputWinner(team2);
            return team2;
        }
        else {
            view.loadToOutputWinner(null);
            return null;
        }
    }

    /**
     * Choose attacker from droidTeam using index of next attacker
     * @param droidTeam team from which attacker is picked
     * @param iAttacker index of attacker droid in droidTeam
     * @return droid who attacks
     */
    private CommonDroid chooseAttacker(DroidTeam droidTeam, int iAttacker) {
        return droidTeam.get(iAttacker);
    }

    /**
     * Choose defender from droidTeam
     * @param droidTeam team from which defender is picked
     * @return droid who defends
     */
    private CommonDroid chooseDefender(DroidTeam droidTeam) {
        int random = randomNumber(0, droidTeam.size() - 1);
        return droidTeam.get(random);
    }

    /**
     * This method applies arena effects to droid
     * @param droid droid on which arena effect applies
     */
    protected abstract void permanentEffect(CommonDroid droid);

    /**
     * This method applies arena effects to droidTeam
     * @param droidTeam team on which arena effect applies
     */
    private void applyPermanentEffect(DroidTeam droidTeam) {
        for (int i = 0; i < droidTeam.size(); i++) {
            permanentEffect(droidTeam.get(i));
        }
    }

    /**
     * Method to save last fight
     */
    public static void saveLastFight() {
        view.saveFight();
    }
}
