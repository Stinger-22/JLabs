package com.labs.complex.command.exception;

public class NoRightException extends RuntimeException {
    private String login;
    private char role;

    public NoRightException(String login, char role) {
        this.login = login;
        this.role = role;
    }

    public NoRightException(String login, char role, String message) {
        super(message);
        this.login = login;
        this.role = role;
    }

    @Override
    public String toString() {
        return "NoRightException{" +
                "login='" + login + '\'' +
                ", role=" + role +
                "} " + super.toString();
    }
}