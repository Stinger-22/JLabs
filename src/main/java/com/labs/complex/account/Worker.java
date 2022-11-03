package com.labs.complex.account;

import java.util.Objects;

/**
 * Class which represents account of worker
 */
public class Worker implements IAccount {
    private String login;
    private String name;
    private String surname;

    /**
     * Worker account constructor
     * @param login login of worker
     * @param name name of worker
     * @param surname surname of worker
     */
    public Worker(String login, String name, String surname) {
        this.login = login;
        this.name = name;
        this.surname = surname;
    }

    /**
     * Get login of worker
     * @return login of worker
     */
    public String getLogin() {
        return login;
    }

    /**
     * Get name of worker
     * @return name of worker
     */
    public String getName() {
        return name;
    }

    /**
     * Geet surname of worker
     * @return surname of worker
     */
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
