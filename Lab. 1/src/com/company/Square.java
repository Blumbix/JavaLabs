package com.company;

import java.util.InputMismatchException;

public class Square extends Figure {
    private double a;

    public Square(double aa){
            a = aa;
    }

    @Override
    public double calculateArea() {
        return a*a;
    }

    @Override
    public double calculatePerimeter() {
        return 4*a;
    }

    @Override
    public void print() {
        System.out.println("\nKwadrat, dane: \nDlugosc boku: "+a+
                "\nPole: "+calculateArea()+"\nObwod: "+calculatePerimeter()+"\n");
    }
}
