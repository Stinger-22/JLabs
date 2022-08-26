package com.labs.math;

import com.labs.one.math.NegativeLucasNumber;
import org.junit.Test;
import static org.junit.Assert.*;

public class NegativeLucasNumberTest {
    @Test
    public void NegativeLucasNumberConstructorTest() {
        try {
            NegativeLucasNumber number1 = new NegativeLucasNumber(1);
            fail("Error \"IllegalArgumentException\" must've been thrown");
        }
        catch (IllegalArgumentException ignored) {
        }
        NegativeLucasNumber number2 = new NegativeLucasNumber(0);
        NegativeLucasNumber number3 = new NegativeLucasNumber(-1);
        NegativeLucasNumber number4 = new NegativeLucasNumber(-10);
        assertEquals(2, number2.getValue());
        assertEquals(-1, number3.getValue());
        assertEquals(123, number4.getValue());
    }
}
