package com.labs.complex.being;

import java.util.Objects;

public class Tax implements Comparable<Tax> {
    private String description;
    private int value;
    private boolean absolute;

    public Tax(String description, int value, boolean absolute) {
        this.description = description;
        this.value = value;
        this.absolute = absolute;
    }

    public String getDescription() {
        return description;
    }

    public int getValue() {
        return value;
    }

    public boolean isAbsolute() {
        return absolute;
    }

    @Override
    public int compareTo(Tax o) {
        return Integer.compare(this.value, o.getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tax tax = (Tax) o;
        return value == tax.value && absolute == tax.absolute && Objects.equals(description, tax.description);
    }

    @Override
    public String toString() {
        return "Tax{description='" + description + '\'' +
                ", value=" + value + ", absolute=" + absolute + '}';
    }
}
