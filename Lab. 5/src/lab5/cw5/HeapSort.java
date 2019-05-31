package lab3.cw5;

public class HeapSort {
    public void heapSort(int arr[])
    {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // One by one extract an element from heap
        for (int i=n-1; i>=0; i--)
        {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }
    void heapify(int arr[], int n, int i)
    {
        int largest = i; // Initialize largest as root
        int l = 2*i + 1; // left = 2*i + 1
        int r = 2*i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    public static void heap(int[] real, int[] opt, int[] pes) {
        //SORT REAL
        long tStart = System.currentTimeMillis();
// uruchom sortowanie.
        HeapSort ob = new HeapSort();
        ob.heapSort(real);

        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        System.out.println("Realistic case: " + elapsedSeconds + "s passed");

//SORT OPT
        tStart = System.currentTimeMillis();
// uruchom sortowanie.
        ob = new HeapSort();
        ob.heapSort(opt);

        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000.0;
        System.out.println("Optimistic case: " + elapsedSeconds + "s passed");

//SORT PES
        tStart = System.currentTimeMillis();
// uruchom sortowanie.
        ob = new HeapSort();
        ob.heapSort(pes);

        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000.0;
        System.out.println("Pesimistic case: " + elapsedSeconds + "s passed");
    }
}
