package io.nimbus.algorithms.sort;


/**
 * taken from https://www.programiz.com/dsa/counting-sort
 */
public class CountingSort {

    static void sort(int[] array, int size) {
        int[] output = new int[size + 1];// +1 to include all numbers including 0 to be counted

        // Find the largest element of the array
        int max = array[0];
        for (int i = 1; i < size; i++) {
            if (array[i] > max)
                max = array[i];
        }
        int[] count = new int[max + 1];// +1 to include all numbers including 0 to be counted

        // Store the count of each element
        for (int i = 0; i < size; i++) {
            count[array[i]]++;
        }

        // Store the cumulative count of each array
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        // Find the index of each element of the original array in count array, and
        // place the elements in output array
        for (int i = size - 1; i >= 0; i--) {
            output[count[array[i]] - 1] = array[i];
            count[array[i]]--;
        }

        // Copy the sorted elements into original array
        for (int i = 0; i < size; i++) {
            array[i] = output[i];
        }
    }

    public static void sort(int[] array) {
        sort(array, array.length);
    }
}