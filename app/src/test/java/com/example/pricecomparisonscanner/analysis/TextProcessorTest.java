package com.example.pricecomparisonscanner.analysis;

import junit.framework.TestCase;

public class TextProcessorTest extends TestCase {

    public void testIsMultiCount1() {
        TextProcessor tp = new TextProcessor();
        assertTrue(tp.isMultiCount("4 cnt Jello"));
    }

    public void testIsMultiCount2() {
        TextProcessor tp = new TextProcessor();
        assertFalse(tp.isMultiCount("Black Sweatpants"));
    }

    public void testIsMultiCount3() {
        TextProcessor tp = new TextProcessor();
        assertTrue(tp.isMultiCount("Marshmallow bags | pack of 8"));
    }

    public void testIsMultiCount4() {
        TextProcessor tp = new TextProcessor();
        assertFalse(tp.isMultiCount("Learn to count: Volume 1"));
    }

    public void testIsMultiCount5() {
        TextProcessor tp = new TextProcessor();
        assertTrue(tp.isMultiCount("3 count of pudding"));
    }
}