package com.labs.complex.account;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestWorker {
    @Test
    public void testWorker() {
        String login = "TestLogin_123";
        String name = "Elizabeth";
        String surname = "Black";
        Worker worker = new Worker(login, name, surname);

        assertEquals(worker.getLogin(), login);
        assertEquals(worker.getName(), name);
        assertEquals(worker.getSurname(), surname);
    }
}
