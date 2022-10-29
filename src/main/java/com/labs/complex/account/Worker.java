package com.labs.complex.account;

public class Worker implements IAccount {
    private String login;
    private String name;
    private String surname;

    public Worker(String login, String name, String surname) {
        this.login = login;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}