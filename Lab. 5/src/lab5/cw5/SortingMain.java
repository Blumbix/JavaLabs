package lab3.cw5;

import java.util.Random;
import java.util.Scanner;

public class SortingMain {

    public static void sortingmain() {
        int n, choice;
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter the size of the array: ");
        n = scan.nextInt();
        //Creating arrays
        int[] real = new int[n];
        int[] opt = new int[n];
        int[] pes = new int[n];
        //Filling
        Random generator = new Random();
        for (int i = 0; i < n; i++) {
            real[i] = generator.nextInt(n);
            opt[i] = i;
            pes[i] = n - i;
        }
        //Switch-case
            System.out.println("Choose sorting method:\n1. Bubble Sort\n2. Merge Sort\n3. Quick Sort\n4. Heap Sort\n5. Comb Sort\n0. Exit");
            choice=scan.nextInt();
            switch(choice){
                case 1:
                    BubbleSort.bubble(real,opt,pes);
                    break;
                case 2:
                    MergeSort.merge(real,opt,pes);
                    break;
                case 3:
                    QuickSort.quick(real,pes);
                    break;
                case 4:
                    HeapSort.heap(real,opt,pes);
                    break;
                case 5:
                    CombSort.comb(real,opt,pes);
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong option - choose again");
                    break;
            }
        }
    }


