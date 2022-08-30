package com.labs.two.transport;

import java.time.LocalDateTime;

public class Train extends Transport {
    private int number;
    private int common;
    private int coupe;
    private int platzkart;
    private int lux;

    public Train(String destination, LocalDateTime departure, int number, int common, int coupe, int platzkart, int lux) {
        super(destination, departure);
        if (number < 0 || common < 0 || coupe < 0 || platzkart < 0 || lux < 0) {
            throw new IllegalArgumentException("All int arguments can't be negative numbers");
        }
        this.number = number;
        this.common = common;
        this.coupe = coupe;
        this.platzkart = platzkart;
        this.lux = lux;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCommon() {
        return common;
    }

    public void setCommon(int common) {
        this.common = common;
    }

    public int getCoupe() {
        return coupe;
    }

    public void setCoupe(int coupe) {
        this.coupe = coupe;
    }

    public int getPlatzkart() {
        return platzkart;
    }

    public void setPlatzkart(int platzkart) {
        this.platzkart = platzkart;
    }

    public int getLux() {
        return lux;
    }

    public void setLux(int lux) {
        this.lux = lux;
    }

    @Override
    public String toString() {
        return "Train{" + "destination='" + destination + '\'' + ", departure='" + departure.format(dateDefaultFormat) +
                "' number=" + number + ", common=" + common + ", coupe=" + coupe + ", platzkart=" + platzkart + '}';
    }
}
