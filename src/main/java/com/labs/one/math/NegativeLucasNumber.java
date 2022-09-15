package com.labs.one.math;

/**
 * Immutable class which is a container for negative Lucas number
 */
public class NegativeLucasNumber {
    private static final NegativeLucasSeries negativeLucasSeries = new NegativeLucasSeries();

    private final int n;
    private final int value;

    /**
     * Constructs container for Lucas number. Finds value of nth Lucas number
     * @param n Nth Lucas number
     */
    public NegativeLucasNumber(int n) {
        if (n > 0) {
            throw new IllegalArgumentException("Parameter number must be negative number or zero.");
        }
        this.value = negativeLucasSeries.get(n);
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

    /**
     * Class which is a container for negative lucas series
     * Example of Negative Lucas series: {..., -11, 7, -4, 3, -1, 2}
     */
    private static class NegativeLucasSeries {
        private final int MAX_N = 45;
        private int generated = 2;
        private final int[] negativeLucasSeries = new int[MAX_N];

        /**
         * Initialize two first numbers for negative Lucas series
         */
        public NegativeLucasSeries() {
            negativeLucasSeries[0] = 2;
            negativeLucasSeries[1] = -1;
        }

        /**
         * Get negative Lucas number by index
         * @param i Index of negative Lucas number. Index should be negative
         * @return Value of ith negative Lucas number
         */
        public int get(int i) {
            if (i < -generated) {
                generateNegativeLucasSeries(i);
            }
            return negativeLucasSeries[-i];
        }

        public void generateNegativeLucasSeries(int n) {
            n = -n;
            int a = negativeLucasSeries[generated - 2], b = negativeLucasSeries[generated - 1], t;
            while (generated <= n) {
                t = a - b;
                a = b;
                b = t;
                negativeLucasSeries[generated] = t;
                generated++;
            }
        }
    }
}
