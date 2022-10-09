package com.labs.complex.account;

public class User extends Account {

    public User(String name, int cardID) {
        super(name, cardID);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", cardID=" + cardID +
                "} " + super.toString();
    }
}
