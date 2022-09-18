package com.labs.three.util;

import java.util.concurrent.ThreadLocalRandom;

public class Math {
    public static int randomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
