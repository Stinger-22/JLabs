package com.labs.complex.menu;

import com.labs.complex.command.Command;

public interface Menu {
    void selectCommand(Command command);
    void show();
}
