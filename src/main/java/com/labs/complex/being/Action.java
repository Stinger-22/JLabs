package com.labs.complex.being;

import java.sql.Date;
import java.util.Objects;

/**
 * Class which represents user actions
 */
public class Action {
    private String name;
    private int value;
    private Date date;
    private float percent;

    /**
     * Action constructor
     * @param name name of action
     * @param value value of action
     * @param date date when action took place
     * @param percent percent from value as tax
     */
    public Action(String name, int value, Date date, float percent) {
        this.name = name;
        this.value = value;
        this.date = date;
        this.percent = percent;
    }

    /**
     * Get name of action
     * @return name of action
     */
    public String getName() {
        return name;
    }

    /**
     * Get value of action
     * @return value of action
     */
    public int getValue() {
        return value;
    }

    /**
     * Get date of action
     * @return date of action
     */
    public Date getDate() {
        return date;
    }

    /**
     * Geet percent from action
     * @return percent from action
     */
    public float getPercent() {
        return percent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Action action = (Action) o;
        return value == action.value && Objects.equals(name, action.name) && Objects.equals(date, action.date);
    }

    @Override
    public String toString() {
        return "Action{name='" + name + "', value=" + value + ", date=" + date + '}';
    }
}
