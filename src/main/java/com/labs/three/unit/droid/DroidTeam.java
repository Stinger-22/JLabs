package com.labs.three.unit.droid;

import com.labs.three.util.ListCircularIterator;

import java.util.ArrayList;
import java.util.List;

public class DroidTeam implements Iterable<Droid>{
    private List<Droid> team;
    private ListCircularIterator<Droid> iterator;

    public DroidTeam(List<Droid> team) {
        this.team = team;
        this.iterator = new ListCircularIterator<>(team);
    }

    public DroidTeam(Droid droid) {
        this.team = new ArrayList<>();
        this.team.add(droid);
        this.iterator = new ListCircularIterator<>(team);
    }

    public void swap(int a, int b) {
        Droid t = team.get(a);
        team.set(a, team.get(b));
        team.set(b, t);
    }

    public boolean isAlive() {
        return team.size() > 0;
    }

    @Override
    public String toString() {
        return "DroidTeam{team=" + team + '}';
    }

    @Override
    public ListCircularIterator<Droid> iterator() {
        return this.iterator;
    }

    public Droid get(int i) {
        return team.get(i);
    }

    public boolean remove(Droid droid) {
        boolean returnValue = team.remove(droid);
        ListCircularIterator<Droid> newIterator = new ListCircularIterator<>(team);
        int i = iterator.getI();
        if (i < team.size()) {
            while (newIterator.getI() != i) {
                newIterator.next();
            }
        }
        this.iterator = newIterator;
        return returnValue;
    }

    public int size() {
        return team.size();
    }
}
