package com.labs.three.arena;

import com.labs.three.droid.CommonDroid;
import com.labs.three.droid.DroidTeam;
import com.labs.three.effect.Effect;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ArenaView {
    private StringBuilder stringBuilder;

    public void printPunchInfo(CommonDroid attacker, CommonDroid defender, int damage, int hit, Effect effect) {
        System.out.println("Attacker: " + attacker);
        if (effect != null) {
            System.out.println("Attacker ability: " + effect);
        }
        System.out.println("Attacker damage: " + damage);
        System.out.println("Defender got hit by: " + hit + " damage");
        System.out.println("Defender: " + defender + "\n\n");
    }

    public void initStringBuilder(DroidTeam team1, DroidTeam team2) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Team 1: ").append(team1).append("\nTeam 2: ").append(team2);
    }

    //TODO: print effect info
    public void loadToStringBuilder(CommonDroid attacker, CommonDroid defender, int damage, int hit) {
        stringBuilder.append("Attacker: ").append(attacker).append("\nAttacker damage: ").append(damage)
                .append("\nDefender got hit by: ").append(hit).append(" damage").append("\nDefender: ").append(defender).append("\n\n");
    }

    public void saveFightEnd(DroidTeam winner) {
        if (winner == null) {
            stringBuilder.append("Draw");
        }
        else {
            stringBuilder.append("The winner is:\n").append(winner);
        }
    }

    public void saveFightToFile(String path) throws FileNotFoundException {
        if (stringBuilder == null) {
            throw new RuntimeException("No fights to save");
        }
        PrintWriter writer = new PrintWriter(path);
        writer.println(stringBuilder.toString());
        writer.close();
    }
}
