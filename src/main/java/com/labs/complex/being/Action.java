package com.labs.complex.being;

import java.sql.Date;
import java.util.Objects;

public class Action {
    private String name;
    private int value;
    private Date date;

    public Action(String name, int value, Date date) {
        this.name = name;
        this.value = value;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public Date getDate() {
        return date;
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
