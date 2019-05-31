package tests;

import lab3.cw1.Matrix;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class MatrixTest {
    Double[][] dataset = {{1., 2., 0.}, {2., 0., 1.}, {0., 2., 1.}};
    Matrix<Double> matrix1 = new Matrix<>(dataset);
    Double[][] dataset2 = {{1., 0., 1.}, {2., 1., 1.}, {2., 0., 3.}};
    Matrix<Double> matrix2 = new Matrix<>(dataset2);
    Matrix<Double> matrix3 = new Matrix<>(matrix1.addition(matrix2));
    Double[][] dataset4 = {{2., 2., 1.}, {4., 1., 2.}, {2., 2., 4.}};
    Matrix<Double> matrix4 = new Matrix<>(dataset2);

    @Test
    public void addition() {
        assertEquals(matrix4, matrix3);
    }

    private void assertEquals(Matrix<Double> expectedArray, Matrix<Double> ourArray) {
        Double x=0.;
        Double y=0.;
        while((expectedArray.hasNext() && ourArray.hasNext())){
            x=expectedArray.next();
            y=ourArray.next();
            if(x!=y){
                assertFalse(false,"Arrays are not equal!");
                break;
            }
        }
    }
}