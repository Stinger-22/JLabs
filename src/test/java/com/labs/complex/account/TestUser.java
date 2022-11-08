package com.labs.complex.account;

import com.labs.complex.being.Action;
import com.labs.complex.being.Benefit;
import com.labs.complex.being.Person;
import com.labs.complex.being.Tax;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestUser {
    @Test
    public void testUser() {
        String login = "TestUser_";
        Person person = new Person("Jack", "Black", 105.50, 2, "Builder", null);
        List<Tax>  taxList = new ArrayList<>();
        List<Benefit> benefitList = new ArrayList<>();
        List<Action> actionList = new ArrayList<>();

        taxList.add(new Tax("Some tax", 100, true));
        benefitList.add(new Benefit("Some benefit"));
        actionList.add(new Action("Get money", 100, new java.sql.Date(System.currentTimeMillis()), (float) 0.1));

        User user = new User(login, person, taxList, benefitList, actionList);

        assertEquals(user.getLogin(), login);
        assertEquals(user.getPerson(), person);
        assertEquals(user.getTaxList(), taxList);
        assertEquals(user.getBenefitList(), benefitList);
        assertEquals(user.getActionList(), actionList);
    }
}
