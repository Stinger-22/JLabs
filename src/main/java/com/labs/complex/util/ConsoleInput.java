package com.labs.complex.util;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Singleton class for handling console input from user
 */
public class ConsoleInput {
    private static Scanner scanner;

    private ConsoleInput() {

    }

    /**
     * Get instance of scanner
     * @return instance of scanner
     */
    public static Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    /**
     * Make ConsoleInput setup new scanner object next time getScanner will be used
     */
    public static void close() {
        scanner = null;
    }
}