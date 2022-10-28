package com.labs.complex.command.worker;

import com.labs.complex.account.IAccount;
import com.labs.complex.account.Worker;
import com.labs.complex.command.Command;
import com.labs.complex.command.exception.AccessDeniedException;

public class CommandRemoveUserBenefit implements Command {
    Worker account;

    public CommandRemoveUserBenefit(IAccount account) throws AccessDeniedException {
        if (!(account instanceof Worker)) {
            throw new AccessDeniedException(account);
        }
        this.account = (Worker) account;
    }

    @Override
    public void execute() {
        System.out.println("Removing benefit for user...");
    }
}
