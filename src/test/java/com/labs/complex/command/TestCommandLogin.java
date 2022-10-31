package com.labs.complex.command;

import com.labs.complex.Application;
import com.labs.complex.account.Admin;
import com.labs.complex.account.User;
import com.labs.complex.account.Worker;
import com.labs.complex.being.Action;
import com.labs.complex.being.Benefit;
import com.labs.complex.being.Person;
import com.labs.complex.being.Tax;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestCommandLogin {
    private static Application application;

    @BeforeClass
    public static void setupApplication() {
        application = new Application();
    }

    @After
    public void logout() {
        new CommandLogout(application).execute();
    }

    @AfterClass
    public static void closeApplication() {
        new CommandExit(application).execute();
    }

    @Test
    public void testLoginAdmin() {
        new CommandLogin(application, "TestAdmin", "testadmin").execute();
        assertEquals(new Admin("TestAdmin"), application.getAccount());
    }

    @Test
    public void testLoginNoRight() {
        new CommandLogin(application, "TestNoAdmin", "testnoadmin").execute();
        new CommandLogin(application, "TestNoWorker", "testnoworker").execute();
        new CommandLogin(application, "TestNoPerson", "testnoperson").execute();
    }

    @Test
    public void testLoginWorker() {
        new CommandLogin(application, "TestWorker", "testworker").execute();
        assertEquals(new Worker("TestWorker", "Test", "Worker"), application.getAccount());
    }

    @Test
    public void testLoginUserWithLists() throws ParseException {
        new CommandLogin(application, "TestPerson", "testperson").execute();
        Person person = new Person("Test", "Person", 0.0, 0, null, null);
        List<Tax> taxList = new ArrayList<>();
        taxList.add(new Tax("TestTax", 50, false));
        List<Benefit> benefitList = new ArrayList<>();
        benefitList.add(new Benefit("TestBenefit"));
        List<Action> actionList = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        actionList.add(new Action("TestAction", 10, new java.sql.Date(dateFormat.parse("2022-10-30").getTime())));
        User user = new User("TestPerson", person, taxList, benefitList, actionList);
        assertEquals(user, application.getAccount());
    }

    @Test
    public void testLoginUserNoLists() {
        new CommandLogin(application, "TestPerson3", "testperson3").execute();
        Person person = new Person("Test", "Person", 0.0, 0, null, null);
        User user = new User("TestPerson3", person, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        assertEquals(user, application.getAccount());
    }
}
