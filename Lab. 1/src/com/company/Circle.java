package com.company;

public class Circle extends Figure {
    private double r;

    public Circle(double rr){
            r = rr;
    }

    @Override
    public double calculateArea() {
        return Math.PI*r*r;
    }

    @Override
    public double calculatePerimeter() {
        return 2*Math.PI*r;
    }

    @Override
    public void print() {
        System.out.println("\nKolo, dane: \nDlugosc promienia: "+r+
                "\nPole: "+calculateArea()+"\nObwod: "+calculatePerimeter()+"\n");
    }
}
