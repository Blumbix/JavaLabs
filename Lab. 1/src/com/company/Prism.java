package com.company;

public class Prism implements Printable {
    private Figure podstawa;
    private double h;

    public Prism (Figure f,double hh){
            h = hh;
            podstawa = f;
    }

    private double calculateArea() {
        return (podstawa.calculateArea() * 2) + (podstawa.calculatePerimeter() * h);
    }

    private double calculateVolume() {
        return podstawa.calculateArea() * h;
    }

    @Override
    public void print() {
        System.out.println("Graniastoslup, dane: \nPole: "+calculateArea()+
                "\nObjetosc: "+calculateVolume());
    }
}
