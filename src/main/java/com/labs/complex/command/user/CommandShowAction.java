package com.labs.complex.command.user;

import com.labs.complex.account.IAccount;
import com.labs.complex.account.User;
import com.labs.complex.command.Command;
import com.labs.complex.command.exception.AccessDeniedException;

public class CommandShowAction implements Command {
    private User account;

    public CommandShowAction(IAccount account) throws AccessDeniedException {
        if (!(account instanceof User)) {
            throw new AccessDeniedException(account);
        }
        this.account = (User) account;
    }

    @Override
    public void execute() {
        System.out.println(account.getActionList());
    }
}
