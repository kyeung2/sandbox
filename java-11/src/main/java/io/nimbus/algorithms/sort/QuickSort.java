package io.nimbus.algorithms.sort;

/**
 * code taken from https://www.programiz.com/dsa/quick-sort
 */
public class QuickSort {

    // Function to partition the array on the basis of pivot element
    static int partition(int[] array, int low, int high) {

        // Select the pivot element
        int pivot = array[high];
        int i = low;

        // Put the elements smaller than pivot on the left and
        // greater than pivot on the right of pivot
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
            }
        }
        int temp = array[i];
        array[i] = array[high];
        array[high] = temp;
        return i;
    }

    static void sort(int[] array, int low, int high) {
        if (low < high) {
            // Select pivot position and put all the elements smaller
            // than pivot on left and greater than pivot on right
            int p = partition(array, low, high);

            // Sort the elements on the left of pivot
            sort(array, low, p - 1);

            // Sort the elements on the right of pivot
            sort(array, p + 1, high);
        }
    }

    public static void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }
}
