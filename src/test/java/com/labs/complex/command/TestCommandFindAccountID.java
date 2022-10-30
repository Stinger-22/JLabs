package com.labs.complex.command;

import com.labs.complex.launch.Application;
import com.labs.complex.command.exception.AccessDeniedException;
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
    public void testCommandFindAccountID() throws AccessDeniedException {
        CommandFindAccountID command = new CommandFindAccountID(application.getAccount(), "TestAdmin");
        command.execute();
        assertEquals(21, (int) command.getId());
    }
}
