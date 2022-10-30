package com.labs.complex.account;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestAdmin {
    @Test
    public void testAdmin() {
        String login = "_TestAdmin_";
        Admin admin = new Admin(login);

        assertEquals(admin.getLogin(), login);
    }
}
