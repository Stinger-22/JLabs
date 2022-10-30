package com.labs.complex;

import com.labs.complex.util.ConsoleInput;
import org.junit.After;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class TestApplication {
    private Application application;
    private final InputStream originalIn = System.in;

    @After
    public void restoreInput() {
        System.setIn(originalIn);
        ConsoleInput.close();
    }

    @Test
    public void testUser() {
        String input = "1\n" +
                "TestPerson2\n" +
                "testperson2\n" +
                "1\n" +
                "2\n" +
                "3\n" +
                "4\n" +
                "Test\n" +
                "5\n" +
                "6\n" +
                "7\n" +
                "8\n" +
                "6\n" +
                "10\n" +
                "9\n" +
                "10\n" +
                "2\n";
        ByteArrayInputStream userInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(userInput);
        application = new Application();
        application.start();
    }

    @Test
    public void testWorker() {
        String input = "1\n" +
                "TestWorker\n" +
                "testworker\n" +
                "1\n" +
                "2\n" +
                "3\n" +
                "TestPerson2\n" +
                "4\n" +
                "10\n" +
                "4\n" +
                "TestPerson2\n" +
                "4\n" +
                "5\n" +
                "TestPerson2\n" +
                "3\n" +
                "6\n" +
                "TestPerson2\n" +
                "3\n" +
                "7\n" +
                "2\n";
        ByteArrayInputStream workerInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(workerInput);
        application = new Application();
        application.start();
    }

    @Test
    public void testAdmin() {
        String input = "1\n" +
                "TestAdmin\n" +
                "testadmin\n" +
                "1\n" +
                "Test\n" +
                "test\n" +
                "t\n" +
                "t\n" +
                "0\n" +
                "0\n" +
                "\n" +
                "\n" +
                "Test\n" +
                "3\n" +
                "1\n" +
                "Test\n" +
                "3\n" +
                "2\n" +
                "Test\n" +
                "3\n" +
                "3\n" +
                "Test\n" +
                "3\n" +
                "4\n" +
                "0\n" +
                "10\n" +
                "3\n" +
                "5\n" +
                "Test\n" +
                "4\n" +
                "TestPerson\n" +
                "5\n" +
                "2\n";
        ByteArrayInputStream adminInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(adminInput);
        application = new Application();
        application.start();
    }
}
