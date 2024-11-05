package com.example.cointosserjavafx;

import java.util.Random;

/**
 * This utility class simulates coin throws and calculates statistics.
 * @author Hannes
 */
public class CoinTosserUtil {
    private static Random rand = new Random();
    private static int counterHead = 0;
    private static int counterNumber = 0;
    private static int counterThrows = 0;
    private static double percentageHead = 0.0;
    private static double percentageNumber = 0.0;
    private static int lastResult = -1;

    private static boolean thrownMultipleTimes = false;


    /**
     * Private constructor to prevent instantiation of the utility class.
     */
    private CoinTosserUtil() {

    }

    /**
     * Simulates a single coin throw, updating counters and returning the result.
     * @return The result of the coin throw (0 for HEAD, 1 for NUMBER).
     */
    public static int throwCoin() {
        thrownMultipleTimes = false;
        counterThrows++;
        int result = rand.nextInt(2);
        lastResult = result;
        processResult(result);
        return result;
    }

    /**
     * Simulates multiple coin throws based on the specified amount.
     * @param amount The number of coin throws to simulate.
     */
    public static void throwMultipleTimes(int amount) {
        //thrownMultipleTimes = true;
        for (int i = 0; i < amount; i++) {
            throwCoin();
        }
        thrownMultipleTimes = true;
    }

    /**
     * Updates counters based on the result of a coin throw.
     * @param result The result of the coin throw (0 for HEAD, 1 for NUMBER).
     */
    private static void processResult(int result) {
        if (result == 0) {
            counterHead++;
        } else {
            counterNumber++;
        }
    }

    /**
     * Calculates and updates the percentage statistics based on the counters.
     */
    private static void calculateStatistics() {
        if (counterThrows != 0) {
            percentageHead = ((double) counterHead / (double) counterThrows) * 100.0;
            percentageNumber = ((double) counterNumber / (double) counterThrows) * 100.0;
        }
    }

    /**
     * Gets the count of HEAD results.
     * @return The count of HEAD results.
     */
    public static int getCounterHead() {
        return counterHead;
    }

    /**
     * Gets the count of NUMBER results.
     * @return The count of NUMBER results.
     */
    public static int getCounterNumber() {
        return counterNumber;
    }

    /**
     * Gets the total count of coin throws.
     * @return The total count of coin throws.
     */
    public static int getCounterThrows() {
        return counterThrows;
    }

    /**
     * Gets the percentage of HEAD results.
     * @return The percentage of HEAD results.
     */
    public static double getPercentageHead() {
        calculateStatistics();
        return percentageHead;
    }

    /**
     * Gets the percentage of NUMBER results.
     * @return The percentage of NUMBER results.
     */
    public static double getPercentageNumber() {
        calculateStatistics();
        return percentageNumber;
    }

    /**
     * Checks if multiple coin throws have been simulated.
     * @return True if multiple coin throws have been simulated, otherwise false.
     */
    public static boolean hasMultipleThrows() {
        return thrownMultipleTimes;
    }

    /**
     * Gets the result of the last coin throw.
     * @return The result of the last coin throw (0 for HEAD, 1 for NUMBER).
     */
    public static int getLastResult() {
        return lastResult;
    }

    /**
     * Resets all counters and percentages to zero.
     */
    public static void resetCounters() {
        counterHead = 0;
        counterNumber = 0;
        counterThrows = 0;
        percentageHead = 0.0;
        percentageNumber = 0.0;
    }
}
