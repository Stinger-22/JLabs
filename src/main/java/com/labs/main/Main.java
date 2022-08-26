package com.labs.main;

import com.labs.one.math.NegativeLucasNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<NegativeLucasNumber> lucasNumbers = new ArrayList<>();
        if (args.length != 0) {
            for (int i = 0; i < args.length; i++) {
                lucasNumbers.add(new NegativeLucasNumber(Integer.parseInt(args[i])));
            }
        }
        else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("How much number do you want to enter? ");
            int n = scanner.nextInt();
            int x;
            for (int i = 0; i < n; i++) {
                x = scanner.nextInt();
                lucasNumbers.add(new NegativeLucasNumber(x));
            }
        }
        for (int i = 0; i < lucasNumbers.size(); i++) {
            System.out.println(lucasNumbers.get(i));
        }
    }
}
