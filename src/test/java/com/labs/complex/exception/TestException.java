package com.labs.complex.exception;

import com.labs.complex.Application;
import com.labs.complex.command.CommandExit;
import com.labs.complex.command.CommandLogin;
import com.labs.complex.command.CommandLogout;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestException {
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
    public void testExceptions() {
        new CommandLogin(application, "TestAdmin", "testadmin").execute();
        String expected;

        AccessDeniedException accessDeniedException = new AccessDeniedException(application.getAccount(), "MESSAGE");
        expected = "AccessDeniedException{account=Admin{login='TestAdmin'}} com.labs.complex.exception.AccessDeniedException: MESSAGE";
        assertEquals(expected, accessDeniedException.toString());

        ExistingLoginException existingLoginException = new ExistingLoginException(application.getAccount(), "LOGIN", "MESSAGE");
        expected = "ExistingLoginException{account=Admin{login='TestAdmin'}, login='LOGIN'} com.labs.complex.exception.ExistingLoginException: MESSAGE";
        assertEquals(expected, existingLoginException.toString());

        NoRightException noRightException = new NoRightException("LOGIN", 'Q', "MESSAGE");
        expected = "NoRightException{login='LOGIN', role=Q} com.labs.complex.exception.NoRightException: MESSAGE";
        assertEquals(expected, noRightException.toString());

        WorkNotExistsException workNotExistsException = new WorkNotExistsException("NOTEXISTS");
        expected = "WorkNotExistsException{work='NOTEXISTS'} com.labs.complex.exception.WorkNotExistsException";
        assertEquals(expected, workNotExistsException.toString());
    }
}
