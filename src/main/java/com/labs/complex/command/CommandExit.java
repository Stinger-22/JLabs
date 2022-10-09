package com.labs.complex.command;

import com.labs.complex.account.IAccount;

public class CommandExit implements Command {
    IAccount account;

    public CommandExit(IAccount account) {
        this.account = account;
    }

    @Override
    public void execute() {
        System.out.println("Exit");
    }
}