package com.labs.one.math;

/**
 * Class which is a container for negative lucas numbers
 * Example of Negative Lucas series: {..., -11, 7, -4, 3, -1, 2}
 */
public class NegativeLucasNumber {
    private int n;
    private int value;

    /**
     * Constructs container for Lucas number. Finds value of nth Lucas number
     * @param n Nth Lucas number
     */
    public NegativeLucasNumber(int n) {
        this.value = findNegativeLucasValue(n);
        this.n = n;
    }

    /**
     * Getter to return N of Lucas number
     * @return Returns n of Lucas number
     */
    public int getN() {
        return n;
    }

    /**
     * Getter to return value of Lucas number
     * @return Returns value of nth Lucas number
     */
    public int getValue() {
        return value;
    }

    /**
     * String representation shows Lucas number value and N position in Lucas series
     * @return string representation of Lucas number object
     */
    @Override
    public String toString() {
        return "LucasNumber{" + "n=" + n + ", value=" + value + '}';
    }

    private int findNegativeLucasValue(int n) {
        if (n > 0) {
            throw new IllegalArgumentException("Parameter number must be negative number or zero.");
        }
        n = -n;
        if (n == 0) {
            return 2;
        }
        if (n == 1) {
            return -1;
        }
        int a = 2, b = 1, t = -1;
        for (int i = 2; i <= n; i++) {
            t = a + b;
            a = b;
            b = t;
        }
        return (n & 1) == 1 ? -t : t;
    }
}
