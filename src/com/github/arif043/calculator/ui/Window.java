package com.github.arif043.calculator.ui;

import com.formdev.flatlaf.FlatLightLaf;
import com.github.arif043.calculator.service.Calculator;
import com.github.arif043.calculator.service.Operator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Arif Ertugrul - 14.06.24
 * @date 14.06.24
 * <p>
 * Window represents the graphical user interface.
 * <p>
 * Shows the whole program including the UI elements.
 */
public class Window {

    /**
     * color used for the operator, function and constant -Buttons
     */
    private static final Color FIRST_COLOR = new Color(0x5D, 0xC2, 0xE3);
    /**
     * the underlying calculator
     */
    private Calculator calculator;
    /**
     * the application window
     */
    private JFrame frame;
    /**
     * Shows the input or the result
     */
    private JTextField resultField;
    /**
     * Creates and initializes the window object, which builds the application window
     *
     * @param calculator the underlying calculator
     */
    public Window(Calculator calculator) {
        this.calculator = calculator;
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        // Set the look and feel
        FlatLightLaf.setup();
        initUI();
    }    /**
     * color used for the number buttons
     */
    private static final Color SECOND_COLOR = Color.WHITE;

    /**
     * Initializes the whole application window
     */
    private void initUI() {
        var mainPanel = new JPanel(new BorderLayout());
        var northPanel = new JPanel(new BorderLayout());

        mainPanel.add(BorderLayout.NORTH, northPanel);

        // Prepare result text field
        resultField = new JTextField(0);
        resultField.setEditable(false);
        resultField.setBackground(SECOND_COLOR);
        resultField.setFont(new Font("Arial", Font.PLAIN, 36));
        northPanel.add(BorderLayout.NORTH, resultField);

        // Init the UI parts
        initNumberPanel(mainPanel);
        initOperatorNorthPanel(northPanel);
        initOperatorEastPanel(mainPanel);
        frame.add(BorderLayout.CENTER, mainPanel);

        frame.setVisible(true);
    }

    /**
     * Initializes the number buttons
     *
     * @param mainPanel the main panel contains the parts
     */
    private void initNumberPanel(JPanel mainPanel) {
        JPanel numberPanel = new JPanel(new GridLayout(4, 3));

        // the listener appends the corresponding number into the result text field
        ActionListener numberListener = e -> resultField.setText(resultField.getText() + ((JButton) e.getSource()).getText());
        for (int number = 9; number > 0; number--)
            numberPanel.add(createButton("" + number, numberListener, SECOND_COLOR, 20, 50, 50));

        // add more buttons into the number panel
        numberPanel.add(createButton(",", this::commaListener, SECOND_COLOR, 20, 60, 60));
        numberPanel.add(createButton("0", numberListener, SECOND_COLOR, 20, 60, 60));
        numberPanel.add(createButton("+/-", this::signListener, SECOND_COLOR, 20, 60, 60));
        mainPanel.add(BorderLayout.CENTER, numberPanel);
    }

    /**
     * Initializes the upper function and constant button panel
     *
     * @param northPanel the north panel contains the function and constant buttons
     */
    private void initOperatorNorthPanel(JPanel northPanel) {
        JPanel gridPanel = new JPanel(new GridLayout(3, 5));

        // create and attach the single buttons
        // maybe I should use constant for the sizes
        gridPanel.add(createButton("←", this::deleteListener, FIRST_COLOR, 20, 60, 40));
        gridPanel.add(createButton("π", e -> resultField.setText(formatValue(Math.PI)), FIRST_COLOR, 20, 60, 40));
        gridPanel.add(createButton("X²", e -> computeFunction(Math.pow(getNumber(), 2)), FIRST_COLOR, 20, 60, 40));
        gridPanel.add(createButton("√", e -> computeFunction(Math.sqrt(getNumber())), FIRST_COLOR, 20, 60, 40));
        gridPanel.add(createButton("e", e -> resultField.setText(formatValue(Math.E)), FIRST_COLOR, 20, 60, 40));
        gridPanel.add(createButton("log", e -> computeFunction(Math.log10(getNumber())), FIRST_COLOR, 20, 60, 40));
        gridPanel.add(createButton("ln", e -> computeFunction(Math.log(getNumber())), FIRST_COLOR, 20, 60, 40));
        gridPanel.add(createButton("X^Y", e -> compute(Operator.SQUARE), FIRST_COLOR, 20, 60, 40));
        gridPanel.add(createButton("sin", e -> computeFunction(Math.round(Math.sin(getNumber()))), FIRST_COLOR, 20, 60, 40));
        gridPanel.add(createButton("cos", e -> computeFunction(Math.round(Math.cos(getNumber()))), FIRST_COLOR, 20, 60, 40));
        gridPanel.add(createButton("tan", e -> computeFunction(Math.round(Math.tan(getNumber()))), FIRST_COLOR, 20, 60, 40));
        gridPanel.add(createButton("asin", e -> computeFunction(Math.asin(getNumber())), FIRST_COLOR, 20, 60, 40));
        gridPanel.add(createButton("acos", e -> computeFunction(Math.acos(getNumber())), FIRST_COLOR, 20, 60, 40));
        gridPanel.add(createButton("atan", e -> computeFunction(Math.atan(getNumber())), FIRST_COLOR, 20, 60, 40));
        gridPanel.add(createButton("C", e -> {
            calculator.reset();
            resultField.setText("");
        }, FIRST_COLOR, 20, 60, 40));
        northPanel.add(BorderLayout.SOUTH, gridPanel);
    }

    /**
     * Initializes the operator buttons on the right
     *
     * @param mainPanel the main panel contains the parts
     */
    private void initOperatorEastPanel(JPanel mainPanel) {
        JPanel gridPanel = new JPanel(new GridLayout(5, 1));
        mainPanel.add(BorderLayout.EAST, gridPanel);
        // create and attach the songle buttons
        gridPanel.add(createButton("x", e -> compute(Operator.TIMES), FIRST_COLOR, 20, 60, 50));
        gridPanel.add(createButton("/", e -> compute(Operator.DVISION), FIRST_COLOR, 20, 60, 50));
        gridPanel.add(createButton("-", e -> compute(Operator.MINUS), FIRST_COLOR, 20, 60, 50));
        gridPanel.add(createButton("+", e -> compute(Operator.PLUS), FIRST_COLOR, 20, 60, 50));
        gridPanel.add(createButton("=", e -> compute(null), FIRST_COLOR, 20, 60, 50));
        mainPanel.add(BorderLayout.EAST, gridPanel);
    }

    /**
     * Creates a button and set some attributes
     *
     * @param text     the showed text
     * @param listener the action listener defines the behavior of the button
     * @param color    background color
     * @param fontSize fontsize of the text
     * @param width    width of the button
     * @param height   height of the button
     * @return the initialized button
     */
    private JButton createButton(String text, ActionListener listener, Color color, int fontSize, int width, int height) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setPreferredSize(new Dimension(width, height));
        button.setFont(new Font("Arial", Font.BOLD, fontSize));
        button.addActionListener(listener);
        return button;
    }

    /**
     * toggles the comma in the input
     */
    private void commaListener(ActionEvent e) {
        if (!resultField.getText().isEmpty()) {
            // toggle
            if (!resultField.getText().contains(","))
                resultField.setText(resultField.getText() + ",");
            else if (resultField.getText().endsWith(","))
                deleteListener(e);
        }
    }

    /**
     * toggles the sign in the input
     */
    private void signListener(ActionEvent e) {
        if (!resultField.getText().isEmpty()) {
            // toggle
            if (!resultField.getText().startsWith("-"))
                resultField.setText("-" + resultField.getText());
            else
                resultField.setText(resultField.getText().substring(1));
        }
    }

    /**
     * deletes the suffix character if exists
     */
    private void deleteListener(ActionEvent e) {
        if (!resultField.getText().isEmpty())
            resultField.setText(resultField.getText().substring(0, resultField.getText().length() - 1));
    }

    /**
     * computes the calculation and shows the result
     */
    private void compute(Operator operator) {
        calculator.compute(getNumber(), operator);
        resultField.setText(operator != null ? "" : formatValue(calculator.getResult()));
    }

    /**
     * computes the function and shows the result
     */
    private void computeFunction(double value) {
        if (!resultField.getText().isEmpty())
            resultField.setText(formatValue(value));
    }

    /**
     * formats the value
     * if the value is an integer, the tailing part becomes cut
     * otherwise nothing happens
     *
     * @param value the value get formatted
     * @return formatted value
     */
    private String formatValue(double value) {
        if (value == Math.floor(value))
            return String.format("%d", (long) value);
        return Double.toString(value);
    }

    /**
     * Returns the value in the text field
     *
     * @return value in the text field
     */
    private double getNumber() {
        return Double.parseDouble(resultField.getText());
    }


}