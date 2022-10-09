package com.labs.complex;

import com.labs.complex.account.*;
import com.labs.complex.command.*;
import com.labs.complex.command.exception.AccessDeniedException;
import com.labs.complex.menu.ConsoleMenu;
import com.labs.complex.menu.Menu;

import java.util.Scanner;

public class ComplexMain {
    public static void main(String[] args) throws AccessDeniedException {
        System.out.println("Hello world!");
        Menu menu = new ConsoleMenu();
        IAccount account = new User("Test", 123);
        int choose = 1;
        Scanner scanner = new Scanner(System.in);
        while (choose != 0) {
            choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    menu.selectCommand(new CommandLogin(account));
                    break;
                case 0:
                    menu.selectCommand(new CommandExit(account));
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
}
