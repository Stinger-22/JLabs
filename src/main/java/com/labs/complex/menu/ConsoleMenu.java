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
        }
    }

    private void specificShow(Admin account) {

    }

    private void specificShow(Worker account) {

    }

    private void specificShow(User account) {

    }
}
