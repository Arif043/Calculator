package com.github.arif043.calculator.service;

import com.github.arif043.calculator.ui.Window;

/**
 * @author Arif Ertugrul
 * @date 14.06.24
 * <p>
 * The main class starts the application
 */
public class Main {

    public static void main(String[] args) {
        new Window(new Calculator());
    }
}