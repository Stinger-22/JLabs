package com.labs.complex.exception;

import com.labs.complex.account.IAccount;

/**
 * Exception should be thrown when specified account shouldn't have access to some commands/methods
 */
public class AccessDeniedException extends Exception {
    private IAccount account;

    /**
     * Exception constructor
     * @param account account which has no access
     */
    public AccessDeniedException(IAccount account) {
        this.account = account;
    }

    /**
     * Exception constructor
     * @param account account which has no access
     * @param message specific message, useful when has additional info about exception
     */
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
