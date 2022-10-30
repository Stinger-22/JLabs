package com.labs.complex.being;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestBeing {
    @Test
    public void testPerson() {
        String name = "Jack";
        String surname = "Black";
        double salary = 105.50;
        int kids = 2;
        String mainWork = "Builder";
        String additionalWork = null;

        Person person = new Person(name, surname, salary, kids, mainWork, additionalWork);
        Person person1 = new Person("Jack", "Black", 105.50, 2, "Builder", null);
        assertEquals(person.getName(), name);
        assertEquals(person.getSurname(), surname);
        assertEquals(person.getSalary(), salary, 0.001);
        assertEquals(person.getKids(), kids);
        assertEquals(person.getMainWork(), mainWork);
        assertEquals(person.getAdditionalWork(), additionalWork);
        assertEquals(person, person1);
    }

    @Test
    public void testAction() {
        String name = "Jack";
        int value = 45;
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());

        Action action = new Action(name, value, date);
        Action action1 = new Action("Jack", 45, date);
        assertEquals(action.getName(), name);
        assertEquals(action.getValue(), value, 0.001);
        assertEquals(action.getDate(), date);
        assertEquals(action, action1);
    }

    @Test
    public void testBenefit() {
        String description = "Testing description";

        Benefit benefit = new Benefit(description);
        Benefit benefit1 = new Benefit("Testing description");
        assertEquals(benefit.getDescription(), description);
        assertEquals(benefit, benefit1);
    }

    @Test
    public void testTax() {
        String description = "Testing description";
        int value = 100;
        boolean absolute = false;

        Tax tax = new Tax(description, value, absolute);

        assertEquals(tax.getDescription(), description);
        assertEquals(tax.getValue(), value);
        assertEquals(tax.isAbsolute(), absolute);

        Tax tax1 = new Tax("Description", 50, true);
        Tax tax2 = new Tax("Testing description", 100, false);

        assertEquals(1, tax.compareTo(tax1));
        assertEquals(-1, tax1.compareTo(tax));

        assertEquals(tax, tax2);
    }
}
