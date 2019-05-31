package tests;

import lab3.cw5.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.rules.Timeout;

import java.util.Random;

public class SortingMainTest {

    int n=99999;
    int[] real = new int[n];
    int[] opt = new int[n];
    int[] pes = new int[n];
    Random generator = new Random();

    @BeforeEach
    public void setup() {
        for (int i = 0; i < n; i++) {
            real[i] = generator.nextInt(n);
            opt[i] = i;
            pes[i] = n - i;
        }
    }

    @Rule
    public Timeout globalTimeout = Timeout.seconds(3);

    @Test
    public void bubbleTest() {
        BubbleSort.bubble(real,opt,pes);
    }
    @Test
    public void quickTest() {
        QuickSort.quick(real,pes);
    }
    @Test
    public void mergeTest() {
        MergeSort.merge(real,opt,pes);
    }
    @Test
    public void heapTest() {
        HeapSort.heap(real,opt,pes);
    }
    @Test
    public void combTest() {
        CombSort.comb(real,opt,pes);
    }
}