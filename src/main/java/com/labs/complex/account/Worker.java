package com.labs.complex.account;

import java.util.Objects;

public class Worker implements IAccount {
    private String login;
    private String name;
    private String surname;

    public Worker(String login, String name, String surname) {
        this.login = login;
        this.name = name;
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return Objects.equals(login, worker.login) && Objects.equals(name, worker.name) && Objects.equals(surname, worker.surname);
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
