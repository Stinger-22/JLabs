package com.labs.complex.exception;

import com.labs.complex.account.IAccount;

public class ExistingLoginException extends Exception {
    private IAccount account;
    private String login;

    public ExistingLoginException(IAccount account, String login) {
        this.account = account;
        this.login = login;
    }

    public ExistingLoginException(IAccount account, String login, String message) {
        super(message);
        this.account = account;
        this.login = login;
    }

    @Override
    public String toString() {
        return "ExistingLoginException{" +
                "account=" + account +
                ", login='" + login + '\'' +
                "} " + super.toString();
    }
}
