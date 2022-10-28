package com.labs.complex;

import com.labs.complex.account.*;
import com.labs.complex.command.*;
import com.labs.complex.command.exception.AccessDeniedException;
import com.labs.complex.db.DBConnection;
import com.labs.complex.menu.ConsoleMenu;
import com.labs.complex.menu.Menu;
import com.labs.complex.util.ConsoleInput;

import java.util.Scanner;

public class Application {
    private final DBConnection dbConnection = DBConnection.getInstance();
    private IAccount account;

    public Application() {
    }

    public void start() throws AccessDeniedException {
        Menu menu = new ConsoleMenu();
        int choose = 1;
        Scanner scanner = ConsoleInput.getScanner();
        while (choose != 0) {
            choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    menu.selectCommand(new CommandLogin(this));
                    break;
                case 0:
                    menu.selectCommand(new CommandExit(this));
                    break;
                case 3:
                    menu.selectCommand(new CommandSearchAccount(account));
                    break;
                default:
                    System.out.println("Wrong command");
                    break;
            }
        }
    }

    public static void main(String[] args) throws AccessDeniedException {
        Application application = new Application();
        application.start();
    }

    public IAccount getAccount() {
        return account;
    }

    public void setAccount(IAccount account) {
        this.account = account;
    }
}
