package com.labs.complex.account;

public class Admin implements IAccount {
    private String login;

    public Admin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return "Admin{login='" + login + "'}";
    }
}
