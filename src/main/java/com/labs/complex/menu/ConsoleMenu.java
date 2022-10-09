package com.labs.complex.menu;

import com.labs.complex.command.Command;

public class ConsoleMenu implements Menu {
    @Override
    public void selectCommand(Command command) {
        command.execute();
    }

    @Override
    public void show() {
        System.out.println("Menu is shown");
    }
}
