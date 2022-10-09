package com.labs.complex.account;

public class Account implements IAccount {
    String name;
    int cardID;

    public Account(String name, int cardID) {
        this.name = name;
        this.cardID = cardID;
    }
}
