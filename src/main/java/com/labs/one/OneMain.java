package com.labs.one;

import com.labs.one.math.NegativeLucasNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OneMain {
    public static void main(String[] args) {
        List<NegativeLucasNumber> lucasNumbers;

        if (args.length != 0) {
            lucasNumbers = inputFromConsole(args);
        }
        else {
            lucasNumbers = inputFromUser();
        }

        printLucasNumbers(lucasNumbers);
    }

    public static List<NegativeLucasNumber> inputFromConsole(String[] args) {
        List<NegativeLucasNumber> lucasNumbers = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            lucasNumbers.add(new NegativeLucasNumber(Integer.parseInt(args[i])));
        }
        return lucasNumbers;
    }

    public static List<NegativeLucasNumber> inputFromUser() {
        List<NegativeLucasNumber> lucasNumbers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("How much numbers do you want to enter? ");
        int n = scanner.nextInt();
        int x;
        for (int i = 0; i < n; i++) {
            x = scanner.nextInt();
            lucasNumbers.add(new NegativeLucasNumber(x));
        }
        return lucasNumbers;
    }

    public static void printLucasNumbers(List<NegativeLucasNumber> lucasNumbers) {
        for (int i = 0; i < lucasNumbers.size(); i++) {
            System.out.println(lucasNumbers.get(i));
        }
    }
}
