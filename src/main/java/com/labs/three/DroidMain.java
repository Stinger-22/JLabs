package com.labs.three;

import com.labs.three.arena.ArenaClassic;
import com.labs.three.unit.droid.CommonDroid;
import com.labs.three.unit.droid.DroidTeam;

public class DroidMain {
    public static void main(String[] args) {
        DroidTeam team1 = new DroidTeam();
        DroidTeam team2 = new DroidTeam();
        team1.addDroid(new CommonDroid("First_1", 100, 7, 11, 0));
        team2.addDroid(new CommonDroid("Second_2", 100, 7, 11, 0));
        team1.addDroid(new CommonDroid("Third_1", 50, 2, 5, 3));

        ArenaClassic arenaClassic = new ArenaClassic(team1, team2);
        DroidTeam winner = arenaClassic.fight();
        System.out.println(winner);
    }

    public static void menu() {

        int choose;

    }
}