package com.labs.three.arena.view;

import com.labs.three.droid.CommonDroid;
import com.labs.three.droid.DroidTeam;
import com.labs.three.effect.Effect;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This class contains all methods related to output and saving arena fight
 */
public class ArenaViewConsole implements ArenaView {
    private StringBuilder stringBuilder;

    /**
     * Show punch in console
     * @param attacker droid who attacks
     * @param defender droid who defends
     * @param damage attacker damage
     * @param hit actual defender health decrease
     * @param effect attacker ability
     */
    public void showPunch(CommonDroid attacker, CommonDroid defender, int damage, int hit, Effect effect) {
        System.out.println("Attacker: " + attacker);
        if (effect != null) {
            System.out.println("Attacker ability: " + effect);
        }
        System.out.println("Attacker damage: " + damage);
        System.out.println("Defender got hit by: " + hit + " damage");
        System.out.println("Defender: " + defender + "\n\n");
    }

    /**
     * This method initializes saving of fight
     */
    public void initOutput() {
        stringBuilder = new StringBuilder();
    }

    /**
     * Save in memory info about fight start and teams, so it can be saved
     * @param team1 first team
     * @param team2 second team
     */
    public void loadToOutputFightStart(DroidTeam team1, DroidTeam team2) {
        stringBuilder.append("Team 1: ").append(team1).append("\nTeam 2: ").append(team2);
    }

    /**
     * Save in memory punch info, so it can be saved
     * @param attacker droid who attacks
     * @param defender droid who defends
     * @param damage attacker damage
     * @param hit actual defender health decrease
     */
    public void loadToOutputPunch(CommonDroid attacker, CommonDroid defender, int damage, int hit) {
        stringBuilder.append("Attacker: ").append(attacker).append("\nAttacker damage: ").append(damage)
                .append("\nDefender got hit by: ").append(hit).append(" damage").append("\nDefender: ").append(defender).append("\n\n");
    }

    /**
     * Save in memory winner, so it can be saved
     * @param winner winner team
     */
    public void loadToOutputWinner(DroidTeam winner) {
        if (winner == null) {
            stringBuilder.append("Draw");
        }
        else {
            stringBuilder.append("The winner is:\n").append(winner);
        }
    }

    /**
     * Save fight in some way
     */
    public void saveFight() {
        if (stringBuilder == null) {
            throw new RuntimeException("No fights to save");
        }
        String path;
        System.out.print("Path to file: ");
        Scanner scanner = new Scanner(System.in);
        path = scanner.next();
        try {
            PrintWriter writer = new PrintWriter(path);
            writer.println(stringBuilder.toString());
            writer.close();
        }
        catch (FileNotFoundException exception) {
            System.out.println("Error. Couldn't write in that file");
        }
    }
}
