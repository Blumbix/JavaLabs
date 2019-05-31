package com.company;

public class Triangle extends Figure {
    private double a,b,c;

    public Triangle(double aa,double bb, double cc){
            a = aa;
            b = bb;
            c = cc;
    }

    @Override
    public double calculateArea() {
        double p = (a+b+c)/2;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }

    @Override
    public double calculatePerimeter() {
        return a+b+c;
    }

    @Override
    public void print() {
        System.out.println("\nTrojkat, dane: \nDlugosci bokow: "+a+", "+b+", "+c+"" +
                "\nPole: "+calculateArea()+"\nObwod: "+calculatePerimeter()+"\n");
    }
}
