package com.labs.complex.being;

import java.sql.Date;

public class Action {
    private String name;
    private double value;
    private Date date;

    public Action(String name, double value, Date date) {
        this.name = name;
        this.value = value;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Action{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
