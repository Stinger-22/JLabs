package com.labs.three.droid;

import com.labs.three.effect.Affectable;
import com.labs.three.effect.Effect;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a collection of droids
 */
public class DroidTeam implements Affectable {
    private List<CommonDroid> team;

    /**
     * Constructor of DroidTeam from List
     * @param team List of droids
     */
    public DroidTeam(List<CommonDroid> team) {
        this.team = team;
    }

    /**
     * Constructor of DroidTeam from one droid
     * @param droid instance of CommonDroid
     */
    public DroidTeam(CommonDroid droid) {
        this.team = new ArrayList<>();
        this.team.add(droid);
    }

    /**
     * Constructor of DroidTeam with no droids
     */
    public DroidTeam() {
        this.team = new ArrayList<>();
    }

    /**
     * Swap droids in team
     * @param a first droid to swap
     * @param b second droid to swap
     */
    public void swap(int a, int b) {
        CommonDroid t = team.get(a);
        team.set(a, team.get(b));
        team.set(b, t);
    }

    /**
     * Add droid to team
     * @param droid droid to add
     */
    public void addDroid(CommonDroid droid) {
        this.team.add(droid);
    }

    /**
     * Remove droid from team
     * @param droid droid to remove
     */
    public void removeDroid(CommonDroid droid) {
        this.team.remove(droid);
    }

    /**
     * Check if team is empty
     * @return true if size of team is not null; false if size of team is zero
     */
    public boolean isEmpty() {
        return team.size() > 0;
    }

    @Override
    public String toString() {
        return "DroidTeam{team=" + team + '}';
    }

    /**
     * Get droid by index from team
     * @param i index of droid
     * @return droid
     */
    public CommonDroid get(int i) {
        return team.get(i);
    }

    /**
     * Get size of team
     * @return size of team
     */
    public int size() {
        return team.size();
    }

    @Override
    public void applyEffect(Effect effect) {
        effect.apply(this);
    }
}
