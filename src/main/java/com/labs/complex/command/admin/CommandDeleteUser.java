package com.labs.complex.command.admin;

import com.labs.complex.account.Admin;
import com.labs.complex.account.IAccount;
import com.labs.complex.command.Command;
import com.labs.complex.command.exception.AccessDeniedException;

public class CommandDeleteUser implements Command {
    private Admin account;

    public CommandDeleteUser(IAccount account) throws AccessDeniedException {
        if (!(account instanceof Admin)) {
            throw new AccessDeniedException(account);
        }
        this.account = (Admin) account;
    }

    @Override
    public void execute() {
        System.out.println("Deleting user...");
    }
}