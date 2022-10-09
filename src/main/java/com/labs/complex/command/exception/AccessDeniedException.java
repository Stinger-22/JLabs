package com.labs.complex.command.exception;

import com.labs.complex.account.IAccount;

public class AccessDeniedException extends Exception {
    IAccount account;

    public AccessDeniedException(IAccount account) {
        this.account = account;
    }

    public AccessDeniedException(IAccount account, String message) {
        super(message);
        this.account = account;
    }

    @Override
    public String toString() {
        return "AccessDeniedException{" +
                "account=" + account +
                "} " + super.toString();
    }
}
