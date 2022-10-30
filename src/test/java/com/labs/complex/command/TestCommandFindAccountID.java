package com.labs.complex.command;

import com.labs.complex.Application;
import com.labs.complex.db.DBConnection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestCommandFindAccountID {
    private Application application;

    @Before
    public void setupApplication() {
        application = new Application();
        new CommandLogin(application, "TestAdmin", "testadmin").execute();
    }

    @Test
    public void testCommandFindAccountID() {
        CommandFindAccountID command = new CommandFindAccountID(application.getAccount(), "TestAdmin");
        command.execute();
        assertEquals(21, (int) command.getId());
    }
}