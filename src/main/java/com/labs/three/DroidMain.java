package com.labs.three;

import com.labs.three.arena.ArenaClassic;
import com.labs.three.unit.droid.CommonDroid;
import com.labs.three.unit.droid.Droid;
import com.labs.three.unit.droid.DroidTeam;

import java.util.ArrayList;
import java.util.List;

public class DroidMain {
    public static void main(String[] args) {
        Droid droid1 = new CommonDroid("First_1", 100, 7, 11, 0);
        Droid droid2 = new CommonDroid("Second_2", 100, 7, 11, 0);
        Droid droid3 = new CommonDroid("Third_1", 50, 2, 5, 3);
        List<Droid> t = new ArrayList<>();
        t.add(droid1);
        t.add(droid3);
        DroidTeam team1 = new DroidTeam(t);
        t = new ArrayList<>();
        t.add(droid2);
        DroidTeam team2 = new DroidTeam(t);
        // TODO: addToTeam and removeFromTeam
        ArenaClassic arenaClassic = new ArenaClassic(team1, team2);
        DroidTeam winner = arenaClassic.fight();
        System.out.println(winner);
    }

    public static void menu() {

        int choose;

    }
}