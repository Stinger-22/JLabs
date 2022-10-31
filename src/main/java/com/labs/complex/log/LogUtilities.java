package com.labs.complex.log;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogUtilities {
    private static FileHandler fileHandler = null;

    static {
        try {
            fileHandler = new FileHandler("applog.txt", false);
            fileHandler.setFormatter(new SimpleFormatter());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static void setupLogger(Logger logger) {
        logger.setUseParentHandlers(false);
        logger.addHandler(fileHandler);
        logger.setLevel(Level.FINEST);
    }
}
