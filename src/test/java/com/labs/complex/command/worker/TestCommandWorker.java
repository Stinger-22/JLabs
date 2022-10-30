package com.labs.complex.command.worker;

import com.labs.complex.being.Person;
import com.labs.complex.command.CommandExit;
import com.labs.complex.command.CommandLogin;
import com.labs.complex.command.CommandLogout;
import com.labs.complex.command.admin.CommandAddUser;
import com.labs.complex.command.admin.CommandDeleteUser;
import com.labs.complex.exception.AccessDeniedException;
import com.labs.complex.launch.Application;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.fail;

public class TestCommandWorker {
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
    public void testWorkerCommandsBenefit() throws AccessDeniedException {
        new CommandLogin(application, "TestWorker", "testworker").execute();
        new CommandAddUserBenefit(application.getAccount(), "TestPerson", 3).execute();
        new CommandRemoveUserBenefit(application.getAccount(), "TestPerson", 3).execute();
    }

    @Test
    public void testWorkerCommandsTax() throws AccessDeniedException {
        new CommandLogin(application, "TestWorker", "testworker").execute();
        new CommandAddUserTax(application.getAccount(), "TestPerson", 3, 10).execute();
        new CommandRemoveUserTax(application.getAccount(), "TestPerson", 3).execute();
    }

    @Test
    public void testWorkerCommandsShow() throws AccessDeniedException {
        new CommandLogin(application, "TestWorker", "testworker").execute();
        System.out.println("TAXES:");
        new CommandShowTaxes(application.getAccount()).execute();
        System.out.println("BENEFITS:");
        new CommandShowBenefits(application.getAccount()).execute();
    }

    @Test
    public void testWorkerCommandsAccess() {
        new CommandLogin(application, "TestPerson", "testperson").execute();
        Person person1 = new Person("Test", "Person", 0.0, 0, null, null);
        try {
            new CommandAddUserTax(application.getAccount(), "TestPerson", 3, 10).execute();
            fail("User can add tax.");
        } catch (AccessDeniedException ignored) {}
        try {
            new CommandRemoveUserTax(application.getAccount(), "TestPerson", 3).execute();
            fail("User can delete user.");
        } catch (AccessDeniedException ignored) {}
        try {
            new CommandAddUserBenefit(application.getAccount(), "TestPerson", 3).execute();
            fail("User can add benefit.");
        } catch (AccessDeniedException ignored) {}
        try {
            new CommandRemoveUserBenefit(application.getAccount(), "TestPerson", 3).execute();
            fail("User can delete benefit.");
        } catch (AccessDeniedException ignored) {}
        try {
            new CommandShowTaxes(application.getAccount()).execute();
            fail("User can show taxes.");
        } catch (AccessDeniedException ignored) {}
        try {
            new CommandShowBenefits(application.getAccount()).execute();
            fail("User can show benefits.");
        } catch (AccessDeniedException ignored) {}
    }
}
