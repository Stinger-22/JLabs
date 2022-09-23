package com.labs.three;

import com.labs.three.arena.ArenaClassic;
import com.labs.three.arena.ArenaVolcano;
import com.labs.three.droid.CommonDroid;
import com.labs.three.droid.DroidEnergyShield;
import com.labs.three.droid.DroidMelting;
import com.labs.three.droid.DroidTeam;

public class DroidMain {
    public static void main(String[] args) {
        CommonDroid droid1 = new CommonDroid("K9-0tron", 100, 7, 11, 0);
        CommonDroid droid2 = new CommonDroid("Framebot", 120, 2, 6, 0);
        CommonDroid droid3 = new DroidEnergyShield("Bubblebot", 40, 5, 17, 2, 40);
        CommonDroid droid4 = new DroidMelting("Steel Rager", 50, 3, 12, -3);

        DroidTeam team1 = new DroidTeam();
        team1.addDroid(droid1);
        team1.addDroid(droid4);
        DroidTeam team2 = new DroidTeam();
        team2.addDroid(droid2);
        team2.addDroid(droid3);

//        ArenaVolcano arenaVolcano = new ArenaVolcano(5);

        ArenaClassic arenaClassic = new ArenaClassic();
        arenaClassic.setTeam1(team1);
        arenaClassic.setTeam2(team2);
        DroidTeam winner = arenaClassic.fight();
        System.out.println("WINNER: " + winner);
    }
}