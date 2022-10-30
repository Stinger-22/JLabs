package com.labs.complex.command.exception;

public class LoginNotExistsException extends Exception {
    private String login;

    public LoginNotExistsException(String login) {
        this.login = login;
    }

    public LoginNotExistsException(String login, String message) {
        super(message);
        this.login = login;
    }

    @Override
    public String toString() {
        return "LoginNotExistsException{" +
                "login='" + login + '\'' +
                "} " + super.toString();
    }
}