package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static double wprowadzDane(){
        Scanner scanner = new Scanner(System.in);
        double x=0;

        try {
            x = scanner.nextDouble();
            if (x <= 0) {
                throw new IllegalArgumentException();
            }
        }
        catch (InputMismatchException ime)
        {
            System.err.println("Wyjatek - Zle dane wejsciowe!");
            scanner.nextLine();
        }
        catch(IllegalArgumentException iae){
            System.err.println("Wyjatek - Dlugosc musi byc wieksza od 0!");
        }
        return x;
    }


    public static void main(String[] args) {
        int number;
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("\nWybierz figure:\n1.Kwadrat.\n2.Trojkat.\n3.Koło.\n0.Wyjscie.");
            number = scanner.nextInt();
            switch (number) {
                case 1:
                    double a1;
                    System.out.print("Podaj bok kwadratu: ");
                    a1=wprowadzDane();
                    if(a1<=0) break;

                    Square kwadrat = new Square(a1);
                    kwadrat.print();

                    double h1;
                    System.out.print("Podaj wysokosc: ");
                    h1=wprowadzDane();
                    if(h1<=0) break;

                    Prism graniastoslup1 = new Prism(kwadrat, h1);
                    graniastoslup1.print();
                    break;

                case 2:
                    double a2,b2,c2;
                    System.out.print("Podaj 1. bok trojkata: ");
                    a2 = wprowadzDane();
                    if(a2<=0) break;
                    System.out.print("Podaj 2. bok trojkata: ");
                    b2 = wprowadzDane();
                    if(b2<=0) break;
                    System.out.print("Podaj 3. bok trojkata: ");
                    c2 = wprowadzDane();
                    if(c2<=0) break;
                    if(a2+b2<=c2 || a2+c2<=b2 || b2+c2<=a2) {
                        System.err.println("Z podanych wartosci nie mozna stworzyc trojkata.");
                        break;
                    }
                    Triangle trojkat = new Triangle(a2,b2,c2);
                    trojkat.print();

                    double h2;
                    System.out.print("Podaj wysokosc: ");
                    h2=wprowadzDane();
                    if(h2<=0) break;
                    Prism graniastoslup2 = new Prism(trojkat, h2);
                    graniastoslup2.print();
                    break;

                case 3:
                    double r1;
                    System.out.print("Podaj promien kola: ");
                    r1=wprowadzDane();
                    if(r1<=0) break;
                    Circle kolo = new Circle(r1);
                    kolo.print();

                    double h3;
                    System.out.print("Podaj wysokosc: ");
                    h3=wprowadzDane();
                    if(h3<=0) break;

                    Prism graniastoslup3 = new Prism(kolo, h3);
                    graniastoslup3.print();
                    break;

                case 0:
                    return;
            }
        }
    }
}
