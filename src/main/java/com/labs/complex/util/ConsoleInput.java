package com.labs.complex.util;

import java.util.Scanner;

public class ConsoleInput {
    private static Scanner scanner;

    private ConsoleInput() {

    }

    public static Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
}