package com.labs.complex.account;

import java.util.Objects;

/**
 * Class which represent admin account
 */
public class Admin implements IAccount {
    private String login;

    /**
     * Admin account constructor
     * @param login admin' login
     */
    public Admin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(login, admin.login);
    }

    @Override
    public String toString() {
        return "Admin{login='" + login + "'}";
    }
}
