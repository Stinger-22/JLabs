package com.labs.complex.command.user;

import com.labs.complex.launch.Application;
import com.labs.complex.account.User;
import com.labs.complex.being.Action;
import com.labs.complex.being.Tax;
import com.labs.complex.command.CommandExit;
import com.labs.complex.command.CommandLogin;
import com.labs.complex.command.CommandLogout;
import com.labs.complex.command.exception.AccessDeniedException;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestCommandUser {
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
    public void testSearchTax() throws AccessDeniedException {
        new CommandLogin(application, "TestPerson1", "testperson1").execute();
        CommandSearchTax commandSearchTax = new CommandSearchTax(application.getAccount(), "1");
        List<Tax> taxList = new ArrayList<>(1);
        taxList.add(new Tax("TestTax1", 50, true));
        commandSearchTax.execute();
        assertEquals(taxList, commandSearchTax.getTaxList());
    }

    @Test
    public void testSortTax() throws AccessDeniedException {
        new CommandLogin(application, "TestPerson1", "testperson1").execute();
        new CommandSortTax(application.getAccount()).execute();
        List<Tax> taxList = new ArrayList<>(1);
        taxList.add(new Tax("TestTax", 10, false));
        taxList.add(new Tax("TestTax1", 50, true));
        assertEquals(taxList, ((User) application.getAccount()).getTaxList());
    }

    @Test
    public void testCalculateTax() throws AccessDeniedException {
        new CommandLogin(application, "TestPerson1", "testperson1").execute();
        CommandCalculateTax command = new CommandCalculateTax(application.getAccount());
        command.execute();

        assertEquals(55.2, command.getValue(), 0.001);
    }

    @Test
    public void testAddAction() throws AccessDeniedException {
        new CommandLogin(application, "TestPerson2", "testperson2").execute();
        CommandAddAction command = new CommandAddAction(application.getAccount(), 6, 10);
        command.execute();
        int size = 0;
        List<Action> actionList = ((User) application.getAccount()).getActionList();
        if (actionList != null) {
            size += actionList.size();
        }
        new CommandLogout(application).execute();
        new CommandLogin(application, "TestPerson2", "testperson2").execute();
        if (command.isAdd()) {
            size++;
        }
        assertEquals(size, ((User) application.getAccount()).getActionList().size());
    }

    @Test
    public void testUserCommandsShow() throws AccessDeniedException {
        new CommandLogin(application, "TestPerson", "testperson").execute();
        new CommandShowUserInfo(application.getAccount()).execute();
        new CommandShowTax(application.getAccount()).execute();
        new CommandShowBenefit(application.getAccount()).execute();
        new CommandShowActions(application.getAccount()).execute();
        new CommandShowAction(application.getAccount()).execute();
    }

    @Test
    public void testUserCommandsAccess() {
        new CommandLogin(application, "TestAdmin", "testadmin").execute();
        try {
            new CommandShowUserInfo(application.getAccount()).execute();
            fail("Another type of account has access to CommandShowUserInfo");
        } catch (AccessDeniedException ignored) {}
        try {
            new CommandShowTax(application.getAccount()).execute();
            fail("Another type of account has access to CommandShowTax");
        } catch (AccessDeniedException ignored) {}
        try {
            new CommandShowBenefit(application.getAccount()).execute();
            fail("Another type of account has access to CommandShowBenefit");
        } catch (AccessDeniedException ignored) {}
        try {
            new CommandShowActions(application.getAccount()).execute();
            fail("Another type of account has access to CommandShowActions");
        } catch (AccessDeniedException ignored) {}
        try {
            new CommandShowAction(application.getAccount()).execute();
            fail("Another type of account has access to CommandShowAction");
        } catch (AccessDeniedException ignored) {}
        try {
            new CommandSearchTax(application.getAccount(), "Test").execute();
            fail("Another type of account has access to CommandSearchTax");
        } catch (AccessDeniedException ignored) {}
        try {
            new CommandSearchTax(application.getAccount(), "Test").execute();
            fail("Another type of account has access to CommandSearchTax");
        } catch (AccessDeniedException ignored) {}
        try {
            new CommandSortTax(application.getAccount()).execute();
            fail("Another type of account has access to CommandSortTax");
        } catch (AccessDeniedException ignored) {}
        try {
            new CommandAddAction(application.getAccount(), 6, 10).execute();
            fail("Another type of account has access to CommandSortTax");
        } catch (AccessDeniedException ignored) {}
        try {
            new CommandCalculateTax(application.getAccount()).execute();
            fail("Another type of account has access to CommandSortTax");
        } catch (AccessDeniedException ignored) {}
    }
}
