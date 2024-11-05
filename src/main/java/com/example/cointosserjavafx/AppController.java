package com.example.cointosserjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;

import java.text.DecimalFormat;

/**
 * This class links the JavaFX GUI with the CoinTosser Util
 * @author Hannes
 */
public class AppController {

    private static final String RESULT_DEFAULT = "Result";
    private static final String RESULT_HEAD = "HEAD!";
    private static final String RESULT_NUMBER = "NUMBER!";
    private static final String RESULT_MULTIPLE_TOSSES = "Multiple coins were tossed!";
    private static final String COIN_QUESTION_MARK = "?";
    private static final String COIN_NUMBER = "1";
    private static final String UNICODE_BUST = "\uD83D\uDC64";
    private static final String ERROR_INVALID_AMOUNT = "Tossing amount must be a number exclusively between 0 and 10,000,000!";
    private static final String TOSSING_AMOUNT_REGEX = "^[0-9]{1,7}$";
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("00.00");


    @FXML
    private Button btn_multipleTosses;
    @FXML
    private TextField txf_multipleTossesAmount;
    @FXML
    private Label lbl_result;
    @FXML
    private Label lbl_coinsTossed;
    @FXML
    private Label lbl_headsTossed;
    @FXML
    private Label lbl_numbersTossed;
    @FXML
    private Label lbl_headPercentage;
    @FXML
    private Label lbl_numberPercentage;
    @FXML
    private Label lbl_coinLabel;
    @FXML
    private Label lbl_errorInvalidTossingAmount;


    /**
     * This method creates a single coin toss
     */
    @FXML
    private void singleToss() {
        CoinTosserUtil.throwCoin();
        setStatisticLabels();
    }

    /**
     * This method allows multiple coin tosses with the amount given by the
     * input TextField
     */
    @FXML
    private void multipleTosses() {
        CoinTosserUtil.throwMultipleTimes(Integer.parseInt(txf_multipleTossesAmount.getText()));
        setStatisticLabels();
    }

    /**
     * This method resets the statistics and the overall tossing process
     */
    @FXML
    private void resetStatistics() {
        CoinTosserUtil.resetCounters();
        setStatisticLabels();
        txf_multipleTossesAmount.clear();
        checkTossingAmountInput();
        lbl_result.setText(RESULT_DEFAULT);
        lbl_coinLabel.setText(COIN_QUESTION_MARK);
    }

    /**
     * This method changes the stylesheet of the application
     * when clicking on the changeTheme button
     */
    @FXML
    private void changeTheme() {
        MainApplication.changeTheme(ThemeChanger.getNextStylesheet());
    }

    /**
     * This method sets the text of statistics labels (results and percentages)
     */
    private void setStatisticLabels() {
        setResultLabel();

        lbl_coinsTossed.setText(String.valueOf(CoinTosserUtil.getCounterThrows()));
        lbl_headsTossed.setText(String.valueOf(CoinTosserUtil.getCounterHead()));
        lbl_numbersTossed.setText(String.valueOf(CoinTosserUtil.getCounterNumber()));
        lbl_headPercentage.setText(String.valueOf(DECIMAL_FORMAT.format(CoinTosserUtil.getPercentageHead())));
        lbl_numberPercentage.setText(String.valueOf(DECIMAL_FORMAT.format(CoinTosserUtil.getPercentageNumber())));
    }

    /**
     * This method sets the result label according to whether
     * - multiple coins were tossed
     * - 0 for HEAD
     * - 1 for NUMBER
     */
    private void setResultLabel() {
        if (CoinTosserUtil.hasMultipleThrows()) {
            lbl_result.setText(RESULT_MULTIPLE_TOSSES);
            lbl_coinLabel.setText(COIN_QUESTION_MARK);
        } else if (CoinTosserUtil.getLastResult() == 0) {
            lbl_result.setText(RESULT_HEAD);
            lbl_coinLabel.setText(UNICODE_BUST);
        } else if (CoinTosserUtil.getLastResult() == 1) {
            lbl_result.setText(RESULT_NUMBER);
            lbl_coinLabel.setText(COIN_NUMBER);
        }
    }

    /**
     * This method checks whether the amount for multiple tosses
     * matches a regex for numbers up to 9.999.999
     * - enables button if input matches the regex
     * - disables button otherwise
     */
    private void checkTossingAmountInput() {
        if (txf_multipleTossesAmount.getText().matches(TOSSING_AMOUNT_REGEX)) {
            btn_multipleTosses.setDisable(false);
            lbl_errorInvalidTossingAmount.setVisible(false);
        } else if (txf_multipleTossesAmount.getText().isEmpty()){
            btn_multipleTosses.setDisable(true);
            lbl_errorInvalidTossingAmount.setVisible(false);
        } else {
            btn_multipleTosses.setDisable(true);
            lbl_errorInvalidTossingAmount.setVisible(true);
        }
    }

    /**
     * This method initializes the controller class
     */
    @FXML
    private void initialize() {
        resetStatistics();
        btn_multipleTosses.setDisable(true);
        lbl_errorInvalidTossingAmount.setText(ERROR_INVALID_AMOUNT);
        lbl_errorInvalidTossingAmount.setVisible(false);

        txf_multipleTossesAmount.setOnKeyTyped(event -> {
            checkTossingAmountInput();
        });
    }
}
