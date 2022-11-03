package com.labs.complex.exception;

/**
 * Exception should be thrown when account exists in DB account table but not in role-specific table
 */
public class NoRightException extends RuntimeException {
    private String login;
    private char role;

    /**
     * Exception constructor
     * @param login login of error-generated login
     * @param role role of login
     */
    public NoRightException(String login, char role) {
        this.login = login;
        this.role = role;
    }

    /**
     * Exception constructor
     * @param login login of error-generated login
     * @param role role of login
     * @param message specific message, useful when has additional info about exception
     */
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