package com.labs.complex.account;

public class User implements IAccount {
    private String login;
    private String name;
    private String surname;
    private double salary;
    private int kids;
    private String mainWork;
    private String additionalWork;

    public User(String login, String name, String surname, double salary, int kids, String mainWork, String additionalWork) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.kids = kids;
        this.mainWork = mainWork;
        this.additionalWork = additionalWork;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", salary=" + salary +
                ", kids=" + kids +
                ", mainWork='" + mainWork + '\'' +
                ", additionalWork='" + additionalWork + '\'' +
                '}';
    }
}
