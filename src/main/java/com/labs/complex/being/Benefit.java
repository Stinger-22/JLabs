package com.labs.complex.being;

public class Benefit {
    private String description;

    public Benefit(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Benefit{" +
                "description='" + description + '\'' +
                '}';
    }
}
