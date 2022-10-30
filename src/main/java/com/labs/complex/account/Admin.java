package com.labs.complex.account;

import java.util.Objects;

public class Admin implements IAccount {
    private String login;

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
