package sample;

import javafx.concurrent.Task;
import java.util.Random;
import static sample.Equation.calc;

public class MonteCarlo extends Task<Double> {

    private int progress = 0;
    private int precision;
    private int MIN;
    private int MAX;
    private double baseArea;

    public MonteCarlo(int precision, int MIN, int MAX, double baseArea) {
        this.precision = precision;
        this.MIN = MIN;
        this.MAX = MAX;
        this.baseArea = baseArea;
    }

    @Override
    protected Double call() throws Exception {

        double hit = 0;
        double counter = 0;
        progress = 0;
        Random random = new Random();

        for(int i = 0; i<precision; i++) {
            if(this.isCancelled()) {
                updateProgress(0,0);
                break;
            }
            //losuje punkt
            double x = MIN + (MAX - MIN) * random.nextDouble();
            double y = MIN + (MAX - MIN) * random.nextDouble();
            counter++;
            //sprawdzam czy spelnia calke
            if(calc(x,y)) {
                hit++;
                progress = i;
                updateProgress(progress, precision);
            }
        }
        //wynik=punkty trafione/wszystkie punkty * pole powierzchni wykresu
        Double result = (hit/counter) * baseArea;
        return result;
    }
}
