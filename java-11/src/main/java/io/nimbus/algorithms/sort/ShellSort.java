package io.nimbus.algorithms.sort;

public class ShellSort {

    // Rearrange elements at each n/2, n/4, n/8, ... intervals
    static void sort(int[] array, int n) {
        for (int interval = n / 2; interval > 0; interval /= 2) {
            for (int i = interval; i < n; i ++) {
                int temp = array[i];//index 4
                int j;
                for (j = i;// index 4
                     j >= interval && array[j - interval] > temp;
                     j -= interval) {
                    array[j] = array[j - interval];
                }
                array[j] = temp;
            }
        }
    }

    public static void sort(int[] array) {
        sort(array, array.length);
    }

}