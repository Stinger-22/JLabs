package com.labs.complex.menu;

import com.labs.complex.account.IAccount;
import com.labs.complex.command.Command;

/**
 * Interface for interacting with any kind of menu
 */
public interface Menu {
    /**
     * Immediately execute command
     * @param command command to execute
     */
    void selectCommand(Command command);

    /**
     * Show menu on screen
     * @param account account for which menu is shown
     */
    void show(IAccount account);
}
