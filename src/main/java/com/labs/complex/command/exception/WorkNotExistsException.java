package com.labs.complex.command.exception;

public class WorkNotExistsException extends Exception {
    String work;

    public WorkNotExistsException(String work) {
        this.work = work;
    }

    public WorkNotExistsException(String work, String message) {
        super(message);
        this.work = work;
    }

    @Override
    public String toString() {
        return "WorkNotExistsException{" +
                "work='" + work + '\'' +
                "} " + super.toString();
    }
}