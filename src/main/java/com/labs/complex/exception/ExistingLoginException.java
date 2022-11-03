package com.labs.complex.exception;

import com.labs.complex.account.IAccount;

/**
 * Exception should be thrown during adding new account to database when login already exists
 */
public class ExistingLoginException extends Exception {
    private IAccount account;
    private String login;

    /**
     * Exception constructor
     * @param account account which tries to add new account to database
     * @param login login which already is used
     */
    public ExistingLoginException(IAccount account, String login) {
        this.account = account;
        this.login = login;
    }

    /**
     * Exception constructor
     * @param account account which tries to add new account to database
     * @param login login which already is used
     * @param message specific message, useful when has additional info about exception
     */
    public ExistingLoginException(IAccount account, String login, String message) {
        super(message);
        this.account = account;
        this.login = login;
    }

    @Override
    public String toString() {
        return "ExistingLoginException{account=" + account + ", login='" + login + "'} " + super.toString();
    }
}
