package com.labs.three.arena;
import com.labs.three.droid.DroidTeam;

/**
 * This interface provides required methods to use Arena instances
 */
public interface IArena {
    /**
     * This method starts fight between two teams. It'll throw exception if any team is null so teams should be set in
     * advance.
     * @return Team winner or null if there is draw
     */
    DroidTeam fight();

    /**
     * Set first team on arena
     * @param team1 first team of droids
     */
    void setTeam1(DroidTeam team1);

    /**
     * Set second team on arena
     * @param team2 second team of droids
     */
    void setTeam2(DroidTeam team2);
}
