package com.labs.two.util;

import com.labs.two.transport.Train;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrainSchedule implements Printable {
    private List<Train> trains;

    /**
     * Construct TrainSchedule from array of trains
     * @param trains array of trains
     */
    public TrainSchedule(Train[] trains) {
        this.trains = Arrays.asList(trains);
    }

    /**
     * Construct TrainSchedule from List of trains
     * @param trains List of trains
     */
    public TrainSchedule(List<Train> trains) {
        this.trains = trains;
    }

    /**
     * Remove train from schedule
     * @param train train to remove
     * @return True if removed. False if given train wasn't in schedule.
     */
    public boolean removeTrain(Train train) {
        return trains.remove(train);
    }

    /**
     * Add train to schedule
     * @param train train to add
     * @return True if added. False if couldn't add.
     */
    public boolean addTrain(Train train) {
        return trains.add(train);
    }

    /**
     * Returns List of trains which have given destination
     * @param destination desired destination of trains
     * @return List of trains with given destination
     */
    public List<Train> searchDest(String destination) {
        List<Train> filteredTrains = new ArrayList<>();
        for (int i = 0; i < trains.size(); i++) {
            if (trains.get(i).getDestination().equals(destination)) {
                filteredTrains.add(trains.get(i));
            }
        }
        return filteredTrains;
    }

    /**
     * Returns List of trains which have given destination and departure after given hour
     * @param destination desired destination of trains
     * @param hour only train which departure after this hour will be included
     * @return List of trains which have given destination and departure after given hour
     */
    public List<Train> searchDestAndAfterHour(String destination, int hour) {
        List<Train> filteredTrains = new ArrayList<>();
        Train train;
        for (int i = 0; i < trains.size(); i++) {
            train = trains.get(i);
            if (train.getDestination().equals(destination) && train.getDeparture().getHour() > hour) {
                    filteredTrains.add(train);
            }
        }
        return filteredTrains;
    }

    /**
     * Returns List of trains which have given destination and has common places
     * @param destination desired destination of trains
     * @return List of trains which have given destination and has common places
     */
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

    /**
     * Print in console
     */
    public void print() {
        for (int i = 0; i < trains.size(); i++) {
            System.out.println(trains.get(i));
        }
    }
}
