package com.labs.complex.command;

import com.labs.complex.Application;
import com.labs.complex.exception.AccessDeniedException;
import org.junit.After;
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

    @After
    public void closeApplication() {
        new CommandLogout(application).execute();
        new CommandExit(application).execute();
    }

    @Test
    public void testCommandFindAccountID() throws AccessDeniedException {
        CommandFindAccountID command = new CommandFindAccountID(application.getAccount(), "TestAdmin");
        command.execute();
        assertEquals(21, (int) command.getId());
    }
}
