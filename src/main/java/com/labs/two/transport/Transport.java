package com.labs.two.transport;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transport {
    protected static DateTimeFormatter dateDefaultFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    protected String destination;
    protected LocalDateTime departure;

    public Transport(String destination, LocalDateTime departure) {
        this.destination = destination;
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    @Override
    public String toString() {
        return "Transport{" + "destination='" + destination + '\'' + ", departure=" + departure.format(dateDefaultFormat) + '}';
    }
}
