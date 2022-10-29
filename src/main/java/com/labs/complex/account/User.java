package com.labs.complex.account;

import com.labs.complex.being.Person;

public class User implements IAccount {
    private String login;
    private Person person;

    public User(String login, Person person) {
        this.login = login;
        this.person = person;
    }

    public String getLogin() {
        return login;
    }

    public Person getPerson() {
        return person;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", person=" + person +
                '}';
    }
}
