package com.labs.complex.command.user;

import com.labs.complex.account.IAccount;
import com.labs.complex.account.User;
import com.labs.complex.command.Command;
import com.labs.complex.exception.AccessDeniedException;

import java.util.Collections;

/**
 * Command class for sorting user taxes by value
 */
public class CommandSortTax implements Command {
    private User account;

    public CommandSortTax(IAccount account) throws AccessDeniedException {
        if (!(account instanceof User)) {
            throw new AccessDeniedException(account);
        }
        this.account = (User) account;
    }

    @Override
    public void execute() {
        Collections.sort(account.getTaxList());
    }
}
