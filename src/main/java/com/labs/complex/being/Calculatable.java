package com.labs.complex.being;

import com.labs.complex.account.User;

public interface Calculatable {
    /**
     * Calculate value of object
     * @param user user which has object
     * @return value of object depending on user
     */
    double calculate(User user);
}
