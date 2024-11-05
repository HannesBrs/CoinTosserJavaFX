package com.example.cointosserjavafx;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class simulates a dialog for coin tossing, allowing users to interactively perform coin throws, view statistics,
 * and reset counters through a command-line interface.
 * @author Hannes
 */
public class CoinTosserDialog {

    //TODO Write Tests?
    //TODO Write Interface or Abstract Class

    private Scanner input;

    private final int THROW = 1;
    private final int THROW_MULT = 2;
    private final int PRINT_STATS = 3;
    private final int RESET = 4;
    private final int END = 0;


    private static final String MENU =
            "1 -> throw coin\n"+
            "2 -> throw multiple times\n" +
            "3 -> print Statistics\n" +
            "4 -> reset\n" +
            "0 -> exit";
    private static final String EXIT_PROGRAM = "exit program...";
    private static final String INVALID_MENU_INPUT = "invalid menu option input";
    private static final String CHOOSE_AMOUNT_THROWS = "How many times do you want to throw the coin?";
    private static final String RESET_COUNTER = "Counters have been reset!";
    private static final String ERROR_INVALID_AMOUNT = "invalid amount (has to be between 0 and 10000";

    /**
     * Constructs a new CoinThrowerDialog, initializing the input Scanner for user interaction.
     * @see Scanner
     */
    public CoinTosserDialog() {
        input = new Scanner(System.in);
    }

    /**
     * Starts the coin tossing program, allowing users to choose various functions until they choose to exit.
     */
    private void startProgram() {
        int function = 0;

        do {
            try {
                printMenue();
                function = functionInput();
                startFunction(function);
            } catch(IllegalArgumentException e) {
                System.out.println(e);
            } catch(InputMismatchException e) {
                System.out.println(e);
                input.nextLine();
            } catch(Exception e) {
                System.out.println(e);
                e.printStackTrace(System.out);
            }
        } while (function != END);
    }

    /**
     * Reads the user's input for the chosen function.
     * @return The user's chosen function.
     */
    private int functionInput() {
        int function = input.nextInt();
        input.nextLine();
        return function;
    }

    /**
     * Prints the menu options for the coin tossing program.
     */
    private void printMenue() {
        System.out.println(MENU);
    }

    /**
     * Executes the chosen function based on the user's input.
     * @param function The user's chosen function.
     */
    private void startFunction(int function) {
        switch (function) {
            case THROW:
                throwCoinDialog();
                printResult();
                printStatistics();
                break;
            case THROW_MULT:
                throwMultipleTimesDialog();
                printResult();
                printStatistics();
                break;
            case PRINT_STATS:
                printStatistics();
                break;
            case RESET:
                resetCountersDialog();
                break;
            case END:
                System.out.println(EXIT_PROGRAM);
                break;
            default:
                System.out.println(INVALID_MENU_INPUT);
                break;
        }
    }

    /**
     * Initiates a single coin toss.
     */
    private void throwCoinDialog() {
        CoinTosserUtil.throwCoin();
    }

    /**
     * Initiates multiple coin tosses based on the user input.
     * @throws IllegalArgumentException when the amount of coin tosses is either <= 0 or >=10000
     */
    private void throwMultipleTimesDialog() {
        System.out.println(CHOOSE_AMOUNT_THROWS);
        int amount = input.nextInt();
        if (amount <= 0 || amount >= 10000) {
            throw new IllegalArgumentException(ERROR_INVALID_AMOUNT);
        }
        CoinTosserUtil.throwMultipleTimes(amount);
    }


    /**
     * Prints the result of the last coin toss.
     * If multiple coins have been tossed, the results are not printed; it only states that
     * there were multiple tosses.
     * If a single coin has been tossed, the result, either head or number is printed.
     */
    private void printResult() {

        StringBuilder sb = new StringBuilder();

        if (CoinTosserUtil.hasMultipleThrows()) {
            sb.append("\nMultiple Coins were thrown!");
        } else if (CoinTosserUtil.getLastResult() == 1) {
            sb.append("\nResult: HEAD");
        } else {
            sb.append("\nResult: NUMBER");
        }

        System.out.println(sb.toString());
    }

    /**
     * Prints statistics related to the coin tosses.
     * The statistic includes the overall amount of coins thrown, heads or numbers as results
     * and their percentage.
     */
    private void printStatistics() {

        StringBuilder sb = new StringBuilder();

        sb.append("\nStatistics:\n");
        sb.append("---------------------------------\n");
        sb.append(String.format("%-20s %s %10d %n", "Coins thrown", "=", CoinTosserUtil.getCounterThrows()));
        sb.append("---------------------------------\n");
        sb.append(String.format("%-20s %s %10d %n", "Heads thrown", "=", CoinTosserUtil.getCounterHead()));
        sb.append(String.format("%-20s %s %10d %n", "Numbers thrown", "=", CoinTosserUtil.getCounterNumber()));
        sb.append("---------------------------------\n");
        sb.append(String.format("%-20s %s %10.2f %n","Head percentage", "=",  CoinTosserUtil.getPercentageHead()));
        sb.append(String.format("%-20s %s %10.2f %n","Number percentage", "=",  CoinTosserUtil.getPercentageNumber()));

        System.out.println(sb.toString());
    }

    /**
     * Resets the counters for coin tosses to zero.
     */
    private void resetCountersDialog() {
        CoinTosserUtil.resetCounters();
        System.out.println(RESET_COUNTER);
    }

    /**
     * Creates the CoinThrowerDialog and initiates the method startProgram().
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        new CoinTosserDialog().startProgram();
    }
}