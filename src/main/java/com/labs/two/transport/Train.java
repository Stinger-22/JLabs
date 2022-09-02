package com.labs.two.transport;

import java.time.LocalDateTime;

/**
 * Extends class Transport to represent Train
 * Has data about it: number of train; seats number: common, coupe, platzkart, lux
 */
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

    /**
     * Get number of train
     * @return number of train
     */
    public int getNumber() {
        return number;
    }

    /**
     * Set number of train
     * @param number number of train
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Get number of common places
     * @return number of common places
     */
    public int getCommon() {
        return common;
    }

    /**
     * Set number of common places
     * @param common number of common places
     */
    public void setCommon(int common) {
        this.common = common;
    }

    /**
     * Get number of coupe places
     * @return number of coupe places
     */
    public int getCoupe() {
        return coupe;
    }

    /**
     * Set number of coupe places
     * @param coupe number of coupe places
     */
    public void setCoupe(int coupe) {
        this.coupe = coupe;
    }

    /**
     * Get number of platzkart places
     * @return number of platzkart places
     */
    public int getPlatzkart() {
        return platzkart;
    }

    /**
     * Set number of platzkart places
     * @param platzkart number of platzkart places
     */
    public void setPlatzkart(int platzkart) {
        this.platzkart = platzkart;
    }

    /**
     * Get number of lux places
     * @return number of lux places
     */
    public int getLux() {
        return lux;
    }

    /**
     * Set number of lux places
     * @param lux number of lux places
     */
    public void setLux(int lux) {
        this.lux = lux;
    }

    @Override
    public String toString() {
        return "Train{" + "destination='" + destination + '\'' + ", departure='" + departure.format(dateDefaultFormat) +
                "' number=" + number + ", common=" + common + ", coupe=" + coupe + ", platzkart=" + platzkart + '}';
    }
}
