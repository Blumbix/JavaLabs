package lab3.cw1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Matrix<T extends Number> implements Iterator<T> {
    private T[][] dataset = null;
    private int rowIndex;
    private int columnIndex;

    public Matrix(T[][] dataset) {
        this.dataset = dataset;
    }

    public <T extends Number> double addDataset(T one, T two){
        return one.doubleValue()+two.doubleValue();
    }

    public Double[][] addition(Matrix m) {
        if(dataset.length == m.dataset.length && dataset[0].length == m.dataset[0].length) {
            Double[][] sum = new Double[dataset.length][dataset[0].length];
            for(int i = 0; i < dataset.length; i++) {
                for(int j = 0; j < dataset[0].length; j++) {
                    sum[i][j] = addDataset(dataset[i][j], m.dataset[i][j]);
                }
            }
            return sum;
        }
        else {
            System.err.println("Addition is not possible. The sizes of matrices are not equal.");
            return null;
        }
    }

    @Override
    public boolean hasNext() {
        if (dataset == null)
            return false;
        if (rowIndex >= dataset.length)
            return false;
        if (columnIndex >= dataset[rowIndex].length &&
                (rowIndex >= dataset.length || rowIndex == dataset.length - 1))
            return false;
        return true;
    }

    @Override
    public T next() {
        if (!hasNext())
            throw new NoSuchElementException();
        if (columnIndex >= dataset[rowIndex].length) {
            rowIndex++;
            columnIndex = 0;
        }
        return dataset[rowIndex][columnIndex++];
    }

    public void print(){
        for(int i=1; hasNext(); i++){
            System.out.print(next()+" ");
            if(i%dataset.length==0){
                System.out.println();
            }
        }
        System.out.println();
    }

    public static void matrixMain() {
        Integer[][] dataset = {{1, 1, 1}, {2,2, 2}, {3, 3, 3}};
        Integer[][] dataset2 = {{3, 3, 3}, {2, 2, 2}, {1, 1, 1}};
        Matrix<Integer> m1 = new Matrix<>(dataset);
        Matrix<Integer> m2 = new Matrix<>(dataset2);

        System.out.println("Macierz 1:");
        m1.print();
        System.out.println("Macierz 2:");
        m2.print();

        Matrix matrix3 = new Matrix(m1.addition(m2));
        System.out.println("Suma:");
        matrix3.print();
    }
}
