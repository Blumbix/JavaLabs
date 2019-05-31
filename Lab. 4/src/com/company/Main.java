package com.company;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Calculator myCalculator = new Calculator("Calculator");
                myCalculator.setVisible(true);
            }
        });
    }
}
