package com.labs.two;

import com.labs.two.transport.Train;
import com.labs.two.util.TrainSchedule;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TwoMain {
    public static void main(String[] args) {
        TrainSchedule trainSchedule = initTrains();
        trainSchedule.print();

        List<Train> filtered = filterByChoose(trainSchedule);

        for (int i = 0; i < filtered.size(); i++) {
            System.out.println(filtered.get(i));
        }
    }

    public static TrainSchedule initTrains() {
        List<Train> trains = new ArrayList<>();
        trains.add(new Train("Rivne", LocalDateTime.of(2022, 9, 24, 16, 30), 103, 500, 200, 0, 0));
        trains.add(new Train("Lutsk", LocalDateTime.of(2022, 9, 22, 14, 0), 99, 300, 0, 200, 0));
        trains.add(new Train("Lutsk", LocalDateTime.of(2022, 9, 23, 15, 0), 98, 300, 0, 200, 0));
        trains.add(new Train("Lutsk", LocalDateTime.of(2022, 9, 19, 17, 0), 97, 300, 0, 200, 0));
        trains.add(new Train("Kyiv", LocalDateTime.of(2022, 9, 13, 19, 0), 201, 300, 0, 400, 10));
        trains.add(new Train("Kyiv", LocalDateTime.of(2022, 9, 11, 18, 0), 202, 300, 0, 400, 10));
        trains.add(new Train("Kyiv", LocalDateTime.of(2022, 8, 17, 14, 0), 203, 0, 200, 0, 50));
        trains.add(new Train("Kyiv", LocalDateTime.of(2022, 8, 20, 11, 0), 204, 0, 300, 200, 30));
        trains.add(new Train("Ternopil", LocalDateTime.of(2022, 8, 15, 19, 0), 73, 100, 0, 200, 0));
        trains.add(new Train("Ternopil", LocalDateTime.of(2022, 8, 16, 11, 30), 74, 0, 300, 200, 30));
        return new TrainSchedule(trains);
    }

    public static List<Train> filterByChoose(TrainSchedule trainSchedule) {
        System.out.println("a: Search by destination");
        System.out.println("b: Search by destination and after X hour");
        System.out.println("c: Search by destination and common places");
        System.out.print("Choose: ");

        Scanner scanner = new Scanner(System.in);
        char choose = scanner.next().charAt(0);
        String destination;
        List<Train> found;
        switch (choose) {
            case 'a':
                System.out.print("Destination: ");
                destination = scanner.next();
                return trainSchedule.searchDest(destination);
            case 'b':
                System.out.print("Destination: ");
                destination = scanner.next();
                System.out.print("Hour: ");
                int hour = scanner.nextInt();
                return trainSchedule.searchDestAndAfterHour(destination, hour);
            case 'c':
                System.out.print("Destination: ");
                destination = scanner.next();
                return trainSchedule.searchDestAndHasCommon(destination);
            default:
                throw new IllegalArgumentException("Invalid input choose.");
        }
    }
}
