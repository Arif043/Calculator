package com.github.arif043.calculator.service;

/**
 * @author Arif Ertugrul
 * @date 14.06.24
 * <p>
 * Calculator can perform basic calculus
 */
public class Calculator {

    /**
     * the current operator that get executed
     */
    private Operator currentOperator;

    /**
     * the result of the calculus
     */
    private double result;

    /**
     * computes the given operator and the values
     *
     * @param value       the second operand
     * @param newOperator the new operator get executed afterwards
     */
    public void compute(double value, Operator newOperator) {
        if (currentOperator == null)
            result = value;
        else
            result = currentOperator.calculate(result, value);

        currentOperator = newOperator;
    }

    /**
     * resets the calculator and sets default values
     */
    public void reset() {
        result = 0;
        currentOperator = null;
    }

    /**
     * Provides the result
     *
     * @return value of the calculation
     */
    public double getResult() {
        return result;
    }
}