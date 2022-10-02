package com.labs.three;

import static com.labs.three.util.Math.randomNumber;

import com.labs.three.arena.*;
import com.labs.three.droid.*;
import com.labs.three.effect.DischargeEnergyShield;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Class which handles game process, user input, menu
 */
public class Game {
    private final List<CommonDroid> droids;
    private final List<IArena> arenas;
    private final Scanner scanner;

    /**
     * Constructor to create new game
     */
    public Game() {
        this.droids = new ArrayList<>();
        initStandardDroids();

        this.arenas = new ArrayList<>();
        initStandardArenas();

        this.scanner = new Scanner(System.in);
    }

    /**
     * Method which starts game
     */
    public void start() {
        while (doMenu() == 1) {

        }
    }

    /**
     * Menu handling
     * @return 1 - Game is in process; 0 - Game ended
     * @throws InputMismatchException invalid input of user
     */
    private int doMenu() throws InputMismatchException {
        printMenu();
        int choose;
        try {
            choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    chooseDroidMenu();
                    choose = scanner.nextInt();
                    CommonDroid newDroid = CreateCustomDroidConsole(choose);
                    if (newDroid != null) {
                        droids.add(newDroid);
                    }
                    break;
                case 2:
                    printDroidList();
                    break;
                case 3:
                    setupDuel();
                    break;
                case 4:
                    setupTeamBattle();
                    break;
                case 5:
                    Arena.saveLastFight();
                    break;
                case 6:
                    readFile();
                    break;
                case 7:
                    return 0;
                default:
                    System.out.println("Wrong input");
                    scanner.nextLine();
            }
            return 1;
        }
        catch (InputMismatchException exception) {
            System.out.println("You should write only numbers to select command.");
            scanner.nextLine();
            return 1;
        }
        catch (FileNotFoundException exception) {
            System.out.println("File not found.");
            scanner.nextLine();
            return 1;
        }
    }

    /**
     * Read file containing saved battle
     * @throws FileNotFoundException file was not found with given path
     */
    private void readFile() throws FileNotFoundException {
        String path;
        System.out.print("Path: ");
        path = scanner.next();
        Scanner fileScanner = new Scanner(new FileReader(path));
        while (fileScanner.hasNext()) {
            System.out.println(fileScanner.nextLine());
        }
    }

    /**
     * Setup fighters for duel
     */
    private void setupDuel() {
        try {
            System.out.print("Choose two droids by number in list of droids\nChoose first droid: ");
            int random = randomNumber(0, arenas.size() - 1);
            arenas.get(random).setTeam1(new DroidTeam(chooseDroid().copy()) );
            System.out.print("Choose second droid: ");
            arenas.get(random).setTeam2(new DroidTeam(chooseDroid().copy()) );
            arenas.get(random).fight();
        }
        catch (IndexOutOfBoundsException exception) {
            System.out.println("Wrong number of droid in list");
        }
    }

    /**
     * Setup fighter for team battle
     */
    private void setupTeamBattle() {
        System.out.println("Choose droids by number in list of droids. Enter -1 to end input.");
        int random = randomNumber(0, arenas.size());
        System.out.println("Choose droids for first team");
        DroidTeam team1 = chooseDroidTeam();
        System.out.println("Choose droids for second team: ");
        DroidTeam team2 = chooseDroidTeam();
        arenas.get(random).setTeam2(team2);

        arenas.get(random).setTeam1(team1);
        arenas.get(random).setTeam2(team2);
        System.out.println("WINNER: " + arenas.get(random).fight());
    }

    /**
     * Print menu of game in console
     */
    private void printMenu() {
        System.out.println("\t\t\t----MENU----");
        System.out.println("1. Create droid");
        System.out.println("2. Show droid list");
        System.out.println("3. Battle 1 vs 1");
        System.out.println("4. Team battle");
        System.out.println("5. Save last battle in file");
        System.out.println("6. Read battle from file");
        System.out.println("7. Exit");
        System.out.print("\tUSER: ");
    }

    /**
     * Create standard droids
     */
    private void initStandardDroids() {
        droids.add(new CommonDroid("K9-0tron", 100, 7, 11, 0));

        droids.add(new CommonDroid("Framebot", 120, 2, 6, 0));

        droids.add(new DroidEnergyShield("Bubblebot", 40, 5, 17, 2, 40));

        droids.add(new DroidMelting("Steel Rager", 50, 3, 12, -3, 5, 3));

        droids.add(new CommonDroid("DISCHARGOTRON", 70, 5, 7, 2));
        droids.get(droids.size() - 1).setEffect(new DischargeEnergyShield(15));

//        droids.add(new CommonDroid("Upgraded Repair Bot", 35, 1, 3, 0));
//        droids.get(droids.size() - 1).setEffect(new RepairTeam(4));
    }

    /**
     * Initialize arenas
     */
    private void initStandardArenas() {
        arenas.add(new ArenaClassic());
        arenas.add(new ArenaVolcano(-3));
    }

    /**
     * Choose droid from list on user input
     * @return chosen droid
     */
    private CommonDroid chooseDroid() {
        int i = scanner.nextInt();
        return droids.get(i - 1);
    }

    /**
     * Choose droids to create team
     * @return team from chosen droids
     */
    private DroidTeam chooseDroidTeam() {
        DroidTeam team = new DroidTeam();
        int choose;
        while (true) {
            choose = scanner.nextInt();
            if (choose == -1) {
                break;
            }
            try {
                team.addDroid(droids.get(choose - 1));
            }
            catch (IndexOutOfBoundsException exception) {
                System.out.println("Wrong number of droid in list");
            }
        }
        return team;
    }

    /**
     * Create droid with custom characteristics
     * @param choose droid type
     * @return created droid
     */
    private CommonDroid CreateCustomDroidConsole(int choose) {
        try {
            String name;
            int healthMax, damageMin, damageMax, armor;
            System.out.print("Name: ");
            name = scanner.next();
            System.out.print("Health: ");
            healthMax = scanner.nextInt();
            System.out.print("Min damage: ");
            damageMin = scanner.nextInt();
            System.out.print("Max damage: ");
            damageMax = scanner.nextInt();
            System.out.print("Armor: ");
            armor = scanner.nextInt();
            switch (choose) {
                case 1:
                    return new CommonDroid(name, healthMax, damageMin, damageMax, armor);
                case 2:
                    System.out.print("Energy shield: ");
                    int energyShield = scanner.nextInt();
                    return new DroidEnergyShield(name, healthMax, damageMin, damageMax, armor, energyShield);
                case 3:
                    System.out.print("Health selfhurt");
                    int healthHurt = scanner.nextInt();
                    System.out.print("Bonus attack damage: ");
                    int additionalDamage = scanner.nextInt();
                    return new DroidMelting(name, healthMax, damageMin, damageMax, armor, healthHurt, additionalDamage);
                default:
                    System.out.println("Couldn't create droid");
                    return null;
            }
        }
        catch (InputMismatchException exception) {
            System.out.println("Invalid input.");
            scanner.nextLine();
            return null;
        }
    }

    /**
     * Menu of custom droid creation from possible type
     */
    private void chooseDroidMenu() {
        System.out.println("\t\t\t----DROID CREATION----");
        System.out.println("1. Common droid");
        System.out.println("2. Droid energy shield");
        System.out.println("3. Droid melting");
        System.out.print("\tPlayer: ");
    }

    /**
     * Print list of created droids in console
     */
    public void printDroidList() {
        for (int i = 0; i < droids.size(); i++) {
            System.out.println((i + 1) + ": " + droids.get(i));
        }
    }
}