package com.cunningdj.grokJava;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

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
    public void testEquals(Object expected, Object actual, String testTitle) {
        if (expected == actual) {
            printTestSuccess(testTitle);
        } else {
            printTestFailure(testTitle, String.valueOf(expected), String.valueOf(actual));
        }
    }

    public void doubleArrayEquals(double[] expected, double[] actual, String testTitle) {
        if (Arrays.equals(expected, actual)) {
            printTestSuccess(testTitle);
        } else {
            printTestFailure(testTitle, toString(expected), toString(actual));
        }
    }

    public void floatArrayEquals(float[] expected, float[] actual, String testTitle) {
        if (Arrays.equals(expected, actual)) {
            printTestSuccess(testTitle);
        } else {
            printTestFailure(testTitle, toString(expected), toString(actual));
        }
    }

    public void intArrayEquals(int[] expected, int[] actual, String testTitle) {
        if (Arrays.equals(expected, actual)) {
            printTestSuccess(testTitle);
        } else {
            printTestFailure(testTitle, toString(expected), toString(actual));
        }
    }

    public void intListofListsEquals(List<List<Integer>> expected, List<List<Integer>> actual, String testTitle) {
        if (expected.containsAll(actual)) {
            printTestSuccess(testTitle);
        } else {
            printTestFailure(testTitle, toString(expected), toString(actual));
        }
    }

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

    private static String toString(int[] values) {
        if (values == null) {
            return "null";
        }
        if (values.length < 1) {
            return "";
        }
        String str = String.valueOf(values[0]);
        for (int i=1; i < values.length; ++i) {
            str += "-" + String.valueOf(values[i]);
        }
        return str;
    }

    private static String toString(float[] values) {
        if (values == null) {
            return "null";
        }
        if (values.length < 1) {
            return "";
        }
        String str = String.valueOf(values[0]);
        for (int i=1; i < values.length; ++i) {
            str += "-" + String.valueOf(values[i]);
        }
        return str;
    }

    private static String toString(double[] values) {
        if (values == null) {
            return "null";
        }
        if (values.length < 1) {
            return "";
        }
        String str = String.valueOf(values[0]);
        for (int i=1; i < values.length; ++i) {
            str += "-" + String.valueOf(values[i]);
        }
        return str;
    }

    private static String toString(List<List<Integer>> allValues) {
        if (allValues == null) {
            return "null";
        }
        if (allValues.size() < 1) {
            return "";
        }
        String str = "";
        for (int i=0; i < allValues.size(); ++i) {
            if (i > 0) {
                str += ",[";
            } else {
                str += "[";
            }
            for (int j=0; j < allValues.get(i).size(); ++j) {
                if (j > 0) {
                    str += ",";
                }
                str += String.valueOf(allValues.get(i).get(j));
            }
            str += "]";
        }
        return str;
    }

    private static String toString(Object[] values) {
        if (values.length < 1) {
            return "";
        }
        String str = String.valueOf(values[0]);
        for (int i=1; i < values.length; ++i) {
            str += "-" + String.valueOf(values[i]);
        }
        return str;
    }

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