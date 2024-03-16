package org.example;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
    }

    public int add(int a, int b) {
        return a + b;
    }

    public double divide(int a, int b) {
        // if double a, b = 0, a/b = infinity
        try {
            return a/b;
        } catch (ArithmeticException e) {
            e.printStackTrace();
            return Double.NaN;
        }
    }
}
