package lab3.cw4;

public abstract class SolutionTab {
    public static int[] solution(float[] arr, float target) {
        if(arr.length < 2) {
            System.err.println("Array is too short.");
            return null;
        }
        float x = arr[0], y = arr[0];
        int gap=arr.length-1;
        while (gap > 1) {
            gap=gap*10/13;
            if(gap==0){
                gap=1;
            }
            if(gap==9 || gap==10){
                gap=11;
            }
            for(int i = 0; i+gap < arr.length; i++) {
                    if(i==i+gap) {
                        continue;
                    }
                    if(arr[i]+arr[i+gap] == target) {
                        int[] sum = {i, (i+gap)};
                        return sum;
                    }
                }
            }
        System.err.println("No solution, change target");
        return null;

    }

    public static void printArray(float[] arr){
        for(int i=0;i<arr.length;i++) {
            System.out.print(arr[i] + ",");
        }
    }

    public static void tabMain(){
        float[]array = {8,1,3,14,1,2,9,5,6,4};
        float target = 11;
        System.out.println("Array: ");
        printArray(array);
        System.out.println("\nTarget: "+target);
        int[] res = solution(array, target);
        System.out.println("First index: " + (res[0]+1) + " Second index: " + (res[1]+1));
    }
}
