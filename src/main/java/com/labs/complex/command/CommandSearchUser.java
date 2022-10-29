package com.labs.complex.command;

import com.labs.complex.account.Admin;
import com.labs.complex.account.IAccount;
import com.labs.complex.command.exception.AccessDeniedException;

public class CommandSearchUser implements Command {
    private Admin account;

    public CommandSearchUser(IAccount account) throws AccessDeniedException {
        if (!(account instanceof Admin)) {
            throw new AccessDeniedException(account);
        }
        this.account = (Admin) account;
    }

    @Override
    public void execute() {
        System.out.println("Searching user...");
    }
}
