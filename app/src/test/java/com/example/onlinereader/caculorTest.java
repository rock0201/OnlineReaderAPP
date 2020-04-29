package com.example.onlinereader;

import junit.framework.TestCase;

public class caculorTest extends TestCase {

    private caculor mCalculator;
    public void setUp() throws Exception {
        mCalculator = new caculor();
    }



    public void testSum() {
        assertEquals(6d, mCalculator.sum(1d, 5d), 0);
    }

    public void testSubtract() {

        assertEquals(1d, mCalculator.subtract(5d, 4d), 0);
    }

    public void testDivide() {

        assertEquals(4d, mCalculator.divide(20d, 5d), 0);
    }

    public void testMultiply() {

        assertEquals(10d, mCalculator.multiply(2d, 5d), 0);
    }
}