package com.labs.complex.menu;

import com.labs.complex.account.Admin;
import com.labs.complex.account.User;
import com.labs.complex.account.Worker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class TestConsoleMenu {
    private final Menu menu = new ConsoleMenu();
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setupStreams() {
        System.setOut(new PrintStream(out));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testConsoleMenuNoAccount() {
        String menuView = "\t---MENU---\r\n" +
                "1. Log in\r\n" +
                "2. Exit\r\n" +
                "> ";
        menu.show(null);
        assertEquals(menuView, out.toString());
    }

    @Test
    public void testConsoleMenuUser() {
        String menuView = "\t---MENU---\r\n" +
                "1. My information\r\n" +
                "2. Show my taxes\r\n" +
                "3. Show my benefits\r\n" +
                "4. Search taxes\r\n" +
                "5. Sort taxes\r\n" +
                "6. Calculate tax value\r\n" +
                "7. Show all actions\r\n" +
                "8. Add action\r\n" +
                "9. Show my actions\r\n" +
                "10. Log out\r\n" +
                "> ";
        menu.show(new User(null, null, null, null, null));
        assertEquals(menuView, out.toString());
    }

    @Test
    public void testConsoleMenuWorker() {
        String menuView = "\t---MENU---\r\n" +
                "1. Show taxes\r\n" +
                "2. Show benefits\r\n" +
                "3. Add user tax\r\n" +
                "4. Remove user tax\r\n" +
                "5. Add user benefit\r\n" +
                "6. Remove user benefit\r\n" +
                "7. Log out\r\n" +
                "> ";
        menu.show(new Worker(null, null, null));
        assertEquals(menuView, out.toString());
    }

    @Test
    public void testConsoleMenuAdmin() {
        String menuView = "\t---MENU---\r\n" +
                "1. Add user\r\n" +
                "2. Delete user\r\n" +
                "3. Search user\r\n" +
                "4. Show user\r\n" +
                "5. Log out\r\n" +
                "> ";
        menu.show(new Admin(null));
        assertEquals(menuView, out.toString());
    }
}
