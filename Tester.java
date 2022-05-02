package com.cunningdj.grokJava;
import java.util.HashMap;

public class Tester {
    HashMap<String, Integer> testCounter;

    // MAIN
    public static void main(String[] args) {
        // TESTING THE CLASS FUNCTIONALITY
        String testTitle = "DUMMY";
        if (args.length > 0) {
            testTitle = args[0];
        }

        Tester tester = new Tester();

        // Should print DUMMY #1 to #3 successively
        tester.printTestSuccess(testTitle);
        tester.printTestSuccess(testTitle);
        tester.printTestSuccess(testTitle);

        tester.printTestFailure(testTitle, "a", "b");
    }


    // CONSTRUCTOR(S)
    public Tester() {
        testCounter = new HashMap<String, Integer>();
    }


    // PUBLIC
    public void booleanEquals(boolean expected, boolean actual, String testTitle) {
        if (expected == actual) {
            printTestSuccess(testTitle);
        } else {
            printTestFailure(testTitle, String.valueOf(expected), String.valueOf(actual));
        }
    }

    public void listNodeEquals(ListNode expected, ListNode actual, String testTitle) {
        if (expected == actual) {
            printTestSuccess(testTitle);
        } else {
            printTestFailure(testTitle, String.valueOf(expected), String.valueOf(actual));
        }
    }

    public void intEquals(int expected, int actual, String testTitle) {
        if (expected == actual) {
            printTestSuccess(testTitle);
        } else {
            printTestFailure(testTitle, String.valueOf(expected), String.valueOf(actual));
        }
    }

    public void doubleEquals(double expected, double actual, String testTitle) {
        if (expected == actual) {
            printTestSuccess(testTitle);
        } else {
            printTestFailure(testTitle, String.valueOf(expected), String.valueOf(actual));
        }
    }

    public void isNull(Object actual, String testTitle) {
        if (actual == null) {
            printTestSuccess(testTitle);
        } else {
            printTestFailure(testTitle, String.valueOf(null), String.valueOf(actual));
        }
    }


    // PRIVATE
    private void updateTestCounter(String testTitle) {
        if (!testCounter.containsKey(testTitle)) {
            testCounter.put(testTitle, 0);
        }
        testCounter.put(testTitle, testCounter.get(testTitle) + 1);
    }

    private void printTestSuccess(String testTitle) {
        updateTestCounter(testTitle);
        int count = testCounter.get(testTitle);
        System.out.format("Test %s #%d: SUCCESS\n", testTitle, count);
    }

    private void printTestFailure(String testTitle, String expected, String actual) {
        updateTestCounter(testTitle);
        int count = testCounter.get(testTitle);
        System.out.format("Test %s #%d: FAILURE (Expected: %s  Actual: %s)\n", testTitle, count, expected, actual);
    }
}