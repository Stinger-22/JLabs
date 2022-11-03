package com.labs.complex;

import com.labs.complex.account.Admin;
import com.labs.complex.account.IAccount;
import com.labs.complex.account.User;
import com.labs.complex.account.Worker;
import com.labs.complex.being.Person;
import com.labs.complex.command.CommandExit;
import com.labs.complex.command.CommandLogin;
import com.labs.complex.command.CommandLogout;
import com.labs.complex.command.admin.CommandAddUser;
import com.labs.complex.command.admin.CommandDeleteUser;
import com.labs.complex.command.admin.CommandShowUserPerson;
import com.labs.complex.command.admin.search.CommandSearchUser;
import com.labs.complex.command.admin.search.CommandSearchUserSalary;
import com.labs.complex.command.user.*;
import com.labs.complex.command.worker.*;
import com.labs.complex.db.DBConnection;
import com.labs.complex.exception.AccessDeniedException;
import com.labs.complex.log.LogUtilities;
import com.labs.complex.menu.ConsoleMenu;
import com.labs.complex.menu.Menu;
import com.labs.complex.util.ConsoleInput;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {
    private final DBConnection dbConnection = DBConnection.getInstance();
    private IAccount account;
    private Menu menu;

    private static final Logger logger = Logger.getLogger(Application.class.getName());

    public Application() {

    }

    /**
     * Make application run
     */
    public void start() {
        LogUtilities.setupLogger(logger);
        menu = new ConsoleMenu();
        Scanner scanner = ConsoleInput.getScanner();
        int choose;
        do {
            menu.show(account);
            try {
                choose = scanner.nextInt();
            }
            catch (InputMismatchException exception) {
                scanner.nextLine();
                choose = -1;
            }
        } while (select(account, choose));
    }

    /**
     * Get currently logged in account
     * @return currently logged in account
     */
    public IAccount getAccount() {
        return account;
    }

    /**
     * Set currently logged in account
     */
    public void setAccount(IAccount account) {
        this.account = account;
    }

    /**
     * Select appropriate submenu for handling user input depending on type of logged in account
     * @param account account which is logged in
     * @param choose number of picked command
     * @return true if application continues running; false if user exited from program
     */
    public boolean select(IAccount account, int choose) {
        try {
            if (account instanceof Admin) {
                specificSelect((Admin) account, choose);
            }
            else if (account instanceof Worker) {
                specificSelect((Worker) account, choose);
            }
            else if (account instanceof User) {
                specificSelect((User) account, choose);
            }
            else {
                switch (choose) {
                    case 1:
                        logger.log(Level.INFO, "SELECTED CommandLogin");
                        Scanner scanner = ConsoleInput.getScanner();
                        System.out.print("Login: ");
                        String login = scanner.next();
                        System.out.print("Password: ");
                        String password = scanner.next();
                        menu.selectCommand(new CommandLogin(this, login, password));
                        break;
                    case 2:
                        logger.log(Level.INFO, "SELECTED CommandExit");
                        menu.selectCommand(new CommandExit(this));
                        return false;
                    default:
                        System.out.println("Wrong command");
                        break;
                }
            }
        }
        catch (AccessDeniedException exception) {
            logger.log(Level.SEVERE, "AccessDenied to command which is shown in menu", exception);
            new CommandExit(this);
        }
        return true;
    }

    private void specificSelect(Admin account, int choose) throws AccessDeniedException {
        String login;
        switch (choose) {
            case 1:
                System.out.print("Login: ");
                login = ConsoleInput.getScanner().next();
                System.out.print("Password: ");
                String password = ConsoleInput.getScanner().next();
                try {
                    Person person = consoleCreatePerson();
                    menu.selectCommand(new CommandAddUser(account, login, password, person));
                }
                catch (InputMismatchException exception) {
                    System.out.println("Wrong input");
                    ConsoleInput.getScanner().nextLine();
                }
                break;
            case 2:
                System.out.print("Login: ");
                login = ConsoleInput.getScanner().next();
                menu.selectCommand(new CommandDeleteUser(account, login));
                break;
            case 3:
                chooseSearch();
                break;
            case 4:
                System.out.print("Login: ");
                login = ConsoleInput.getScanner().next();
                CommandShowUserPerson command = new CommandShowUserPerson(account, login);
                menu.selectCommand(command);
                if (command.getPerson() == null) {
                    System.out.println("User not found");
                }
                else {
                    System.out.println(command.getPerson());
                }
                break;
            case 5:
                menu.selectCommand(new CommandLogout(this));
                break;
            default:
                System.out.println(choose);
                System.out.println("Wrong command");
                break;
        }
    }

    private void chooseSearch() throws AccessDeniedException {
        System.out.print("Search by:\n1. Login\n2. Name\n3. Surname\n4. Salary\n5. Work\n> ");
        int choose = ConsoleInput.getScanner().nextInt();
        switch (choose) {
            case 1:
                System.out.print("Login: ");
                menu.selectCommand(new CommandSearchUser(account, choose, ConsoleInput.getScanner().next()));
                break;
            case 2:
                System.out.print("Name: ");
                menu.selectCommand(new CommandSearchUser(account, choose, ConsoleInput.getScanner().next()));
                break;
            case 3:
                System.out.print("Surname: ");
                menu.selectCommand(new CommandSearchUser(account, choose, ConsoleInput.getScanner().next()));
                break;
            case 4:
                System.out.print("Min salary: ");
                double min = ConsoleInput.getScanner().nextDouble();
                System.out.print("Min salary: ");
                double max = ConsoleInput.getScanner().nextDouble();
                menu.selectCommand(new CommandSearchUserSalary(account, min, max));
                break;
            case 5:
                System.out.print("Work: ");
                menu.selectCommand(new CommandSearchUser(account, choose, ConsoleInput.getScanner().next()));
                break;
            default:
                System.out.println("Wrong command");
                break;
        }
    }

    private void specificSelect(Worker account, int choose) throws AccessDeniedException {
        String login;
        int id, value;
        switch (choose) {
            case 1:
                menu.selectCommand(new CommandShowTaxes(account));
                break;
            case 2:
                menu.selectCommand(new CommandShowBenefits(account));
                break;
            case 3:
                try {
                    System.out.print("Login: ");
                    login = ConsoleInput.getScanner().next();
                    System.out.print("TaxID: ");
                    id = ConsoleInput.getScanner().nextInt();
                    System.out.print("Value: ");
                    value = ConsoleInput.getScanner().nextInt();
                    menu.selectCommand(new CommandAddUserTax(account, login, id, value));
                }
                catch (InputMismatchException exception) {
                    System.out.println("Wrong input");
                    ConsoleInput.getScanner().nextLine();
                }
                break;
            case 4:
                try {
                    System.out.print("Login: ");
                    login = ConsoleInput.getScanner().next();
                    System.out.print("TaxID: ");
                    id = ConsoleInput.getScanner().nextInt();
                    menu.selectCommand(new CommandRemoveUserTax(account, login, id));
                }
                catch (InputMismatchException exception) {
                    System.out.println("Wrong input");
                    ConsoleInput.getScanner().nextLine();
                }
                break;
            case 5:
                try {
                    System.out.print("Login: ");
                    login = ConsoleInput.getScanner().next();
                    System.out.print("BenefitID: ");
                    id = ConsoleInput.getScanner().nextInt();
                    menu.selectCommand(new CommandAddUserBenefit(account, login, id));
                }
                catch (InputMismatchException exception) {
                    System.out.println("Wrong input");
                    ConsoleInput.getScanner().nextLine();
                }
                break;
            case 6:
                try {
                    System.out.print("Login: ");
                    login = ConsoleInput.getScanner().next();
                    System.out.print("BenefitID: ");
                    id = ConsoleInput.getScanner().nextInt();
                    menu.selectCommand(new CommandRemoveUserBenefit(account, login, id));
                }
                catch (InputMismatchException exception) {
                    System.out.println("Wrong input");
                    ConsoleInput.getScanner().nextLine();
                }
                break;
            case 7:
                menu.selectCommand(new CommandLogout(this));
                break;
            default:
                System.out.println("Wrong command");
                break;
        }
    }

    private void specificSelect(User account, int choose) throws AccessDeniedException {
        switch (choose) {
            case 1:
                logger.log(Level.INFO, "SELECTED CommandShowUserInfo");
                menu.selectCommand(new CommandShowUserInfo(account));
                break;
            case 2:
                logger.log(Level.INFO, "SELECTED CommandShowTax");
                menu.selectCommand(new CommandShowTax(account));
                break;
            case 3:
                logger.log(Level.INFO, "SELECTED CommandShowBenefit");
                menu.selectCommand(new CommandShowBenefit(account));
                break;
            case 4:
                System.out.println("1. Search by name");
                System.out.println("2. Search by value");
                System.out.print("> ");
                choose = ConsoleInput.getScanner().nextInt();
                switch (choose) {
                    case 1:
                        logger.log(Level.INFO, "SELECTED CommandSearchTaxName");
                        ConsoleInput.getScanner().nextLine();
                        System.out.print("Searching tax with name: ");
                        CommandSearchTaxName commandSearchTax = new CommandSearchTaxName(account, ConsoleInput.getScanner().nextLine());
                        menu.selectCommand(commandSearchTax);
                        System.out.println(commandSearchTax.getTaxList());
                        break;
                    case 2:
                        logger.log(Level.INFO, "SELECTED CommandSearchTaxValue");
                        ConsoleInput.getScanner().nextLine();
                        int min, max;
                        System.out.print("Min tax value: ");
                        min = ConsoleInput.getScanner().nextInt();
                        System.out.print("Max tax value: ");
                        max = ConsoleInput.getScanner().nextInt();
                        CommandSearchTaxValue commandSearchTaxValue = new CommandSearchTaxValue(account, min, max);
                        menu.selectCommand(commandSearchTaxValue);
                        System.out.println(commandSearchTaxValue.getTaxList());
                        break;
                    default:
                        System.out.println("Wrong command");
                        break;
                }
                break;
            case 5:
                logger.log(Level.INFO, "SELECTED CommandSortTax");
                menu.selectCommand(new CommandSortTax(account));
                break;
            case 6:
                logger.log(Level.INFO, "SELECTED CommandCalculateTax");
                CommandCalculateTax commandCalculateTax = new CommandCalculateTax(account);
                menu.selectCommand(commandCalculateTax);
                System.out.println("Month tax: " + commandCalculateTax.getValue());
                break;
            case 7:
                logger.log(Level.INFO, "SELECTED CommandShowActions");
                menu.selectCommand(new CommandShowActions(account));
                break;
            case 8:
                logger.log(Level.INFO, "SELECTED CommandAddAction");
                try {
                    System.out.print("ActionID: ");
                    int id = ConsoleInput.getScanner().nextInt();
                    System.out.print("Value: ");
                    int value = ConsoleInput.getScanner().nextInt();
                    menu.selectCommand(new CommandAddAction(account, id, value));
                }
                catch (InputMismatchException exception) {
                    System.out.println("Wrong input");
                    ConsoleInput.getScanner().nextLine();
                }
                break;
            case 9:
                logger.log(Level.INFO, "SELECTED CommandShowAction");
                menu.selectCommand(new CommandShowAction(account));
                break;
            case 10:
                logger.log(Level.INFO, "SELECTED CommandLogout");
                menu.selectCommand(new CommandLogout(this));
                break;
            default:
                System.out.println("Wrong command");
                break;
        }
    }

    private Person consoleCreatePerson() {
        Scanner scanner = ConsoleInput.getScanner();
        System.out.print("Name: ");
        String name = scanner.next();
        System.out.print("Surname: ");
        String surname = scanner.next();
        System.out.print("Salary: ");
        double salary = scanner.nextDouble();
        System.out.print("Number of kids: ");
        int kids = scanner.nextInt();
        System.out.print("Main work name: ");
        scanner.nextLine();
        String mainWork = scanner.nextLine();
        if (mainWork.length() == 0) {
            mainWork = null;
        }
        System.out.print("Additional work name: ");
        String additionalWork = scanner.nextLine();
        if (additionalWork.length() == 0) {
            additionalWork = null;
        }
        return new Person(name, surname, salary, kids, mainWork, additionalWork);
    }
}
