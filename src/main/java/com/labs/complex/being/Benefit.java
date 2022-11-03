package com.labs.complex.being;

import java.util.Objects;

/**
 * Class which represents different benefits
 */
public class Benefit {
    private String description;

    /**
     * Benefit constructor
     * @param description description of benefit
     */
    public Benefit(String description) {
        this.description = description;
    }

    /**
     * Get description of benefit
     * @return description of benefit
     */
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Benefit benefit = (Benefit) o;
        return Objects.equals(description, benefit.description);
    }

    @Override
    public String toString() {
        return "Benefit{description='" + description + "'}";
    }
}
