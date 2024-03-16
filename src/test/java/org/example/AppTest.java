package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */

public class AppTest {

    @Test
    void testAdd() {
        App app = new App();
        int result = app.add(2, 3);
        assertEquals(5, result, "2 + 3 = 5");
    }

    @Test
    void testDivideValid() {
        App app = new App();
        double result = app.divide(4, 2);
        assertEquals(2.0, result, "4 / 2 = 2.0");
    }

    @Test
    void testDivideByZero() {
        App app = new App();
        double result = app.divide(4, 0);
        assertEquals(Double.NaN, result, "Can't divide by Zero");
    }
}
