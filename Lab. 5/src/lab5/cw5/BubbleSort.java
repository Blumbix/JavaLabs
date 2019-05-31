package lab3.cw5;

public class BubbleSort {

    void bubbleSort(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }

    public static void bubble(int[] real, int[] opt, int[] pes) {
        //SORT REAL
        long tStart = System.currentTimeMillis();
// uruchom sortowanie.
        BubbleSort ob = new BubbleSort();
        ob.bubbleSort(real);

        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        System.out.println("Realistic case: " + elapsedSeconds + "s passed");

//SORT OPT
        tStart = System.currentTimeMillis();
// uruchom sortowanie.
        ob = new BubbleSort();
        ob.bubbleSort(opt);

        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000.0;
        System.out.println("Optimistic case: " + elapsedSeconds + "s passed");

//SORT PES
        tStart = System.currentTimeMillis();
// uruchom sortowanie.
        ob = new BubbleSort();
        ob.bubbleSort(pes);

        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000.0;
        System.out.println("Pesimistic case: " + elapsedSeconds + "s passed");
    }
}
