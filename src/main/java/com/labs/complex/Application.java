package com.labs.complex;

import com.labs.complex.account.*;
import com.labs.complex.being.Person;
import com.labs.complex.command.*;
import com.labs.complex.command.admin.CommandAddUser;
import com.labs.complex.command.admin.CommandDeleteUser;
import com.labs.complex.command.admin.CommandShowUser;
import com.labs.complex.command.exception.AccessDeniedException;
import com.labs.complex.command.user.*;
import com.labs.complex.command.worker.CommandAddUserBenefit;
import com.labs.complex.command.worker.CommandAddUserTax;
import com.labs.complex.command.worker.CommandRemoveUserBenefit;
import com.labs.complex.command.worker.CommandRemoveUserTax;
import com.labs.complex.db.DBConnection;
import com.labs.complex.menu.ConsoleMenu;
import com.labs.complex.menu.Menu;
import com.labs.complex.util.ConsoleInput;

import java.util.Scanner;

public class Application {
    private final DBConnection dbConnection = DBConnection.getInstance();
    private IAccount account;
    private Menu menu;

    public Application() {

    }

    public void start() {
        menu = new ConsoleMenu();
        Scanner scanner = ConsoleInput.getScanner();
        int choose;
        do {
            menu.show(account);
            choose = scanner.nextInt();
        } while (select(account, choose));
    }

    public static void main(String[] args) {
        Application application = new Application();
        application.start();
    }

    public IAccount getAccount() {
        return account;
    }

    public void setAccount(IAccount account) {
        this.account = account;
    }

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
                        menu.selectCommand(new CommandLogin(this));
                        break;
                    case 2:
                        menu.selectCommand(new CommandExit(this));
                        return false;
                    default:
                        System.out.println("Wrong command");
                        break;
                }
            }
        }
        catch (AccessDeniedException exception) {
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

                menu.selectCommand(new CommandAddUser(account, login, password, consoleCreatePerson()));
                break;
            case 2:
                System.out.print("Login: ");
                login = ConsoleInput.getScanner().next();
                menu.selectCommand(new CommandDeleteUser(account, login));
                break;
            case 3:
                menu.selectCommand(new CommandSearchUser(account));
                break;
            case 4:
                System.out.print("Login: ");
                login = ConsoleInput.getScanner().next();
                menu.selectCommand(new CommandShowUser(account, login));
                break;
            case 5:
                menu.selectCommand(new CommandLogout(this));
                break;
            default:
                System.out.println("Wrong command");
                break;
        }
    }

    private void specificSelect(Worker account, int choose) throws AccessDeniedException {
        switch (choose) {
            case 1:
                menu.selectCommand(new CommandAddUserTax(account));
                break;
            case 2:
                menu.selectCommand(new CommandRemoveUserTax(account));
                break;
            case 3:
                menu.selectCommand(new CommandAddUserBenefit(account));
                break;
            case 4:
                menu.selectCommand(new CommandRemoveUserBenefit(account));
                break;
            case 5:
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
                menu.selectCommand(new CommandShowTax(account));
                break;
            case 2:
                menu.selectCommand(new CommandShowBenefit(account));
                break;
            case 3:
                menu.selectCommand(new CommandSearchTax(account));
                break;
            case 4:
                menu.selectCommand(new CommandSortTax(account));
                break;
            case 5:
                menu.selectCommand(new CommandCalculateTax(account));
                break;
            case 6:
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
        System.out.print("Surame: ");
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
