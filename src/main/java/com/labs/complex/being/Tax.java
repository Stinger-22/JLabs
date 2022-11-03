package com.labs.complex.being;

import java.util.Objects;

/**
 * Class which represents different taxes
 */
public class Tax implements Comparable<Tax> {
    private String description;
    private int value;
    private boolean absolute;

    /**
     * Tax constructor
     * @param description description of tax
     * @param value value of tax
     * @param absolute value of tax calculates directly as value (true) or as percentage "value" from person salary (false)
     */
    public Tax(String description, int value, boolean absolute) {
        this.description = description;
        this.value = value;
        this.absolute = absolute;
    }

    /**
     * Get description of tax
     * @return description of tax
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get value of tax
     * @return value of tax
     */
    public int getValue() {
        return value;
    }

    /**
     * Is tax absoulute
     * @return boolean value if tax is absolute
     */
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
