package com.labs.complex.command.admin;

import com.labs.complex.Application;
import com.labs.complex.account.Admin;
import com.labs.complex.account.User;
import com.labs.complex.being.Person;
import com.labs.complex.command.Command;
import com.labs.complex.command.CommandExit;
import com.labs.complex.command.CommandLogin;
import com.labs.complex.command.CommandLogout;
import com.labs.complex.command.exception.AccessDeniedException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestCommandAdmin {
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
    public void testAdminCommands() {
        new CommandLogin(application, "TestAdmin", "testadmin").execute();
        Person person1 = new Person("Test", "Person", 0.0, 0, null, null);
        Person person2 = new Person("Test", "Person", 0.0, 0, "TestWork", "TestWork1");
        Person person3 = new Person("Test", "Person", 0.0, 0, "NOTEXISTS", null);
        try {
            CommandAddUser addUser1 = new CommandAddUser(application.getAccount(), "TestAddUser1", "testadduser1", person1);
            CommandAddUser addUser2 = new CommandAddUser(application.getAccount(), "TestAddUser2", "testadduser2", person2);
            CommandAddUser addUser3 = new CommandAddUser(application.getAccount(), "TestAddUser3", "testadduser3", person3);
            addUser1.execute();
            addUser2.execute();
            addUser3.execute();
        }
        catch (AccessDeniedException exception) {
            fail("Admin have no access to CommandAddUser.");
        }
        try {
            CommandShowUserPerson commandShowUserExisting1 = new CommandShowUserPerson(application.getAccount(), "TestAddUser1");
            CommandShowUserPerson commandShowUserExisting2 = new CommandShowUserPerson(application.getAccount(), "TestAddUser2");
            commandShowUserExisting1.execute();
            commandShowUserExisting2.execute();
            assertEquals(person1, commandShowUserExisting1.getPerson());
            assertEquals(person2, commandShowUserExisting2.getPerson());
        }
        catch (AccessDeniedException exception) {
            fail("Admin have no access to CommandShowUserPerson.");
        }
        try {
            CommandDeleteUser commandDeleteUser1 = new CommandDeleteUser(application.getAccount(), "TestAddUser1");
            CommandDeleteUser commandDeleteUser2 = new CommandDeleteUser(application.getAccount(), "TestAddUser2");
            commandDeleteUser1.execute();
            commandDeleteUser2.execute();
        }
        catch (AccessDeniedException exception) {
            fail("Admin have no access to CommandDeleteUser.");
        }
        try {
            CommandShowUserPerson commandShowUserDeleted1 = new CommandShowUserPerson(application.getAccount(), "TestAddUser1");
            CommandShowUserPerson commandShowUserDeleted2 = new CommandShowUserPerson(application.getAccount(), "TestAddUser2");
            CommandShowUserPerson commandShowUserDeleted3 = new CommandShowUserPerson(application.getAccount(), "TestAddUser3");
            commandShowUserDeleted1.execute();
            commandShowUserDeleted2.execute();
            commandShowUserDeleted3.execute();
            assertNull(commandShowUserDeleted1.getPerson());
            assertNull(commandShowUserDeleted2.getPerson());
            assertNull(commandShowUserDeleted3.getPerson());
        }
        catch (AccessDeniedException exception) {
            fail("Admin have no access to CommandShowUserPerson.");
        }
    }

    @Test
    public void testAdminCommandsAccess() {
        new CommandLogin(application, "TestPerson", "testperson").execute();
        Person person1 = new Person("Test", "Person", 0.0, 0, null, null);
        try {
            new CommandAddUser(application.getAccount(), "TestAddUser1", "testadduser1", person1).execute();
            fail("User can add user.");
        }
        catch (AccessDeniedException ignored) {
        }
        try {
            new CommandDeleteUser(application.getAccount(), "TestAddUser1").execute();
            fail("User can delete user.");
        }
        catch (AccessDeniedException ignored) {
        }
    }
}
