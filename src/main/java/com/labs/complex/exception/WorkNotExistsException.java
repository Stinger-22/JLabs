package com.labs.complex.exception;

/**
 * Exception should be thrown when work is not found in DB
 */
public class WorkNotExistsException extends Exception {
    private String work;

    /**
     * Exception constructor
     * @param work work name
     */
    public WorkNotExistsException(String work) {
        this.work = work;
    }

    /**
     * Exception constructor
     * @param work work name
     * @param message specific message, useful when has additional info about exception
     */
    public WorkNotExistsException(String work, String message) {
        super(message);
        this.work = work;
    }

    @Override
    public String toString() {
        return "WorkNotExistsException{work='" + work + "'} " + super.toString();
    }
}