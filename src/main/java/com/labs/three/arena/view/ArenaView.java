package com.labs.three.arena.view;

import com.labs.three.droid.CommonDroid;
import com.labs.three.droid.DroidTeam;
import com.labs.three.effect.Effect;

/**
 * This interface gives required methods to show output information from arena and save it
 */
public interface ArenaView {
    /**
     * Show punch
     * @param attacker droid who attacks
     * @param defender droid who defends
     * @param damage attacker damage
     * @param hit actual defender health decrease
     * @param effect attacker ability
     */
    void showPunch(CommonDroid attacker, CommonDroid defender, int damage, int hit, Effect effect);

    /**
     * This method initializes saving of fight
     */
    void initOutput();

    /**
     * Save in memory info about fight start and teams, so it can be saved
     * @param team1 first team
     * @param team2 second team
     */
    void loadToOutputFightStart(DroidTeam team1, DroidTeam team2);

    /**
     * Save in memory punch info, so it can be saved
     * @param attacker droid who attacks
     * @param defender droid who defends
     * @param damage attacker damage
     * @param hit actual defender health decrease
     */
    void loadToOutputPunch(CommonDroid attacker, CommonDroid defender, int damage, int hit);

    /**
     * Save in memory winner, so it can be saved
     * @param winner winner team
     */
    void loadToOutputWinner(DroidTeam winner);

    /**
     * Save fight in some way
     */
    void saveFight();
}
