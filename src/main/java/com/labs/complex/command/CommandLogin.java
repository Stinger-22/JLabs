package com.labs.complex.command;

import com.labs.complex.account.IAccount;

public class CommandLogin implements Command {
    IAccount account;

    public CommandLogin(IAccount account) {
        this.account = account;
    }

    @Override
    public void execute() {
        System.out.println("Login");
    }
}
