package com.labs.two.util;

import com.labs.two.transport.Train;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrainSchedule implements Printable {
    private List<Train> trains;

    public TrainSchedule(Train[] trains) {
        this.trains = Arrays.asList(trains);
    }

    public TrainSchedule(List<Train> trains) {
        this.trains = trains;
    }

    public boolean removeTrain(Train t) {
        return trains.remove(t);
    }

    public boolean addTrain(Train t) {
        return trains.add(t);
    }

    public List<Train> searchDest(String destination) { // stream?
        List<Train> filteredTrains = new ArrayList<>();
        for (int i = 0; i < trains.size(); i++) {
            if (trains.get(i).getDestination().equals(destination)) {
                filteredTrains.add(trains.get(i));
            }
        }
        return filteredTrains;
    }

    public List<Train> searchDestAndAfterHour(String destination, int hour) {
        List<Train> filteredTrains = new ArrayList<>();
        Train train;
        for (int i = 0; i < trains.size(); i++) {
            train = trains.get(i);
            if (train.getDestination().equals(destination) && train.getDeparture().getHour() > hour) {  // ?
                    filteredTrains.add(train);
            }
        }
        return filteredTrains;
    }

    public List<Train> searchDestAndHasCommon(String destination) {
        List<Train> filteredTrains = new ArrayList<>();
        Train train;
        for (int i = 0; i < trains.size(); i++) {
            train = trains.get(i);
            if (train.getDestination().equals(destination) && train.getCommon() > 0) {
                filteredTrains.add(train);
            }
        }
        return filteredTrains;
    }

    public void print() {
        for (int i = 0; i < trains.size(); i++) {
            System.out.println(trains.get(i));
        }
    }
}
