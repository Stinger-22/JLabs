package com.labs.two.transport;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class Transport has basic data: destination and departure time
 * Time has default printing format: dd-MM-yyyy HH:mm
 */
public class Transport {
    protected static DateTimeFormatter dateDefaultFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    protected String destination;
    protected LocalDateTime departure;

    /**
     * Constructor to create instance of Transport
     * @param destination destination place of transport
     * @param departure LocalDateTime object represents departure time
     */
    public Transport(String destination, LocalDateTime departure) {
        this.destination = destination;
        this.departure = departure;
    }

    /**
     * Get destination
     * @return String destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Set destination
     * @param destination String destination
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Get departure date and time
     * @return LocalDateTime departure time
     */
    public LocalDateTime getDeparture() {
        return departure;
    }

    /**
     * Set departure time
     * @param departure Instance of LocalDateTime
     */
    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    @Override
    public String toString() {
        return "Transport{" + "destination='" + destination + '\'' + ", departure=" + departure.format(dateDefaultFormat) + '}';
    }
}
