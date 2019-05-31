package lab3.cw5;

public class CombSort {
    int getNextGap(int gap)
    {
        // Shrink gap by Shrink factor
        gap = (gap*10)/13;
        if (gap < 1)
            return 1;
        return gap;
    }

    // Function to sort arr[] using Comb Sort
    void combSort(int arr[])
    {
        int n = arr.length;

        // initialize gap
        int gap = n;

        // Initialize swapped as true to make sure that
        // loop runs
        boolean swapped = true;

        // Keep running while gap is more than 1 and last
        // iteration caused a swap
        while (gap != 1 || swapped == true)
        {
            // Find next gap
            gap = getNextGap(gap);

            // Initialize swapped as false so that we can
            // check if swap happened or not
            swapped = false;

            // Compare all elements with current gap
            for (int i=0; i<n-gap; i++)
            {
                if (arr[i] > arr[i+gap])
                {
                    // Swap arr[i] and arr[i+gap]
                    int temp = arr[i];
                    arr[i] = arr[i+gap];
                    arr[i+gap] = temp;

                    // Set swapped
                    swapped = true;
                }
            }
        }
    }

    public static void comb(int[] real, int[] opt, int[] pes) {
        //SORT REAL
        long tStart = System.currentTimeMillis();
// uruchom sortowanie.
        CombSort ob = new CombSort();
        ob.combSort(real);

        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        System.out.println("Realistic case: " + elapsedSeconds + "s passed");

//SORT OPT
        tStart = System.currentTimeMillis();
// uruchom sortowanie.
        ob = new CombSort();
        ob.combSort(opt);

        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000.0;
        System.out.println("Optimistic case: " + elapsedSeconds + "s passed");

//SORT PES
        tStart = System.currentTimeMillis();
// uruchom sortowanie.
        ob = new CombSort();
        ob.combSort(pes);

        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000.0;
        System.out.println("Pesimistic case: " + elapsedSeconds + "s passed");
    }
}
