package lab3.cw5;

public class QuickSort {
    int partition(int[] a, int p, int r) {

        int x = a[p];
        int i = p-1;
        int j = r+1;

        while (true) {
            while (++i < r && a[i] < x);
            while (--j > p && a[j] > x);

            if (i < j) {
                int tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            } else {
                return j;
            }
        }
    }
    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    void quickSort(int[] a, int p, int r)
    {
        while(p<r)
        {
            int q=partition(a,p,r);
            if (q-p <= r-(q+1))
            {
                quickSort(a,p,q);
                p=q+1;
            }
            else
            {
                quickSort(a,q+1,r);
                r=q;
            }
        }
    }

    public static void quick(int[] real, int[] pes) {
        //SORT REAL AND OPT
        long tStart = System.currentTimeMillis();
// uruchom sortowanie.
        QuickSort ob = new QuickSort();
        ob.quickSort(real,0,real.length-1);

        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        System.out.println("Realistic case == Optimistic case: " + elapsedSeconds + "s passed");

//SORT PES
        tStart = System.currentTimeMillis();
// uruchom sortowanie.
        ob = new QuickSort();
        ob.quickSort(pes,0,pes.length-1);

        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000.0;
        System.out.println("Pesimistic case: " + elapsedSeconds + "s passed");
    }
}