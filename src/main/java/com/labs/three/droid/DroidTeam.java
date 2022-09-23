package com.labs.three.droid;

import java.util.ArrayList;
import java.util.List;

public class DroidTeam{
    private List<CommonDroid> team;

    public DroidTeam(List<CommonDroid> team) {
        this.team = team;
    }

    public DroidTeam(CommonDroid droid) {
        this.team = new ArrayList<>();
        this.team.add(droid);
    }

    public DroidTeam() {
        this.team = new ArrayList<>();
    }

    public void swap(int a, int b) {
        CommonDroid t = team.get(a);
        team.set(a, team.get(b));
        team.set(b, t);
    }

    public void addDroid(CommonDroid droid) {
        this.team.add(droid);
    }

    public void removeDroid(CommonDroid droid) {
        this.team.remove(droid);
    }

    public boolean isAlive() {
        return team.size() > 0;
    }

    @Override
    public String toString() {
        return "DroidTeam{team=" + team + '}';
    }


    public CommonDroid get(int i) {
        return team.get(i);
    }

    public int size() {
        return team.size();
    }
}
