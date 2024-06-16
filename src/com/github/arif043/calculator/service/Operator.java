package com.github.arif043.calculator.service;

/**
 * @author Arif Ertugrul
 * @date 14.06.24
 *
 * Represents the operators
 */
public enum Operator {
    PLUS {
        @Override
        public double calculate(double firstValue, double secondValue) {
            return firstValue + secondValue;
        }
    },
    MINUS {
        @Override
        public double calculate(double firstValue, double secondValue) {
            return firstValue - secondValue;
        }
    },
    TIMES {
        @Override
        public double calculate(double firstValue, double secondValue) {
            return firstValue * secondValue;
        }
    },
    DVISION {
        @Override
        public double calculate(double firstValue, double secondValue) {
            return firstValue / secondValue;
        }
    },
    SQUARE {
        @Override
        public double calculate(double firstValue, double secondValue) {
            return Math.pow(firstValue, secondValue);
        }
    };

    /**
     * executes the corresponding operator
     * @param firstValue first operand
     * @param secondValue second operand
     * @return the result
     */
    public double calculate(double firstValue, double secondValue) {
        return 0;
    }
}