package com.labs.complex.menu;

import com.labs.complex.account.Admin;
import com.labs.complex.account.IAccount;
import com.labs.complex.account.User;
import com.labs.complex.account.Worker;
import com.labs.complex.command.Command;

public class ConsoleMenu implements Menu {
    @Override
    public void selectCommand(Command command) {
        command.execute();
    }

    @Override
    public void show(IAccount account) {
        if (account instanceof Admin) {
            specificShow((Admin) account);
        }
        else if (account instanceof Worker) {
            specificShow((Worker) account);
        }
        else if (account instanceof User) {
            specificShow((User) account);
        }
        else {
            System.out.println("\t---MENU---");
            System.out.println("1. Log in");
            System.out.println("2. Exit");
            System.out.print("> ");
        }
    }

    private void specificShow(Admin account) {
        System.out.println("\t---MENU---");
        System.out.println("1. Add user");
        System.out.println("2. Delete user");
        System.out.println("3. Search user");
        System.out.println("4. Show user");
        System.out.println("5. Log out");
        System.out.print("> ");
    }

    private void specificShow(Worker account) {
        System.out.println("\t---MENU---");
        System.out.println("1. Add user tax");
        System.out.println("2. Remove user tax");
        System.out.println("3. Add user benefit");
        System.out.println("4. Remove user benefit");
        System.out.println("5. Log out");
        System.out.print("> ");
    }

    private void specificShow(User account) {
        System.out.println("\t---MENU---");
        System.out.println("1. My information");
        System.out.println("2. Show taxes");
        System.out.println("3. Show benefits");
        System.out.println("4. Search taxes");
        System.out.println("5. Sort taxes");
        System.out.println("6. Calculate tax value");
        System.out.println("7. Log out");
        System.out.print("> ");
    }
}
