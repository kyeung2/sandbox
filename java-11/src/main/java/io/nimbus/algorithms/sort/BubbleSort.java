package io.nimbus.algorithms.sort;

/**
 * Taken from https://www.programiz.com/dsa/bubble-sort
 */
public class BubbleSort {

    public static void sort(int[] array) {
        int size = array.length;

        // Run loops two times: one for walking through the array
        // and the other for comparison
        for (int i = 0; i < size - 1; i++) {

            // swapped keeps track of swapping
            boolean swapped = true;

            // bubbles to the right, each iteration j gets smaller.
            for (int j = 0; j < size - i - 1; j++) {

                // To sort in descending order, change > to < in this line.
                if (array[j] > array[j + 1]) {
                    // Swap if greater is at the rear position
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    swapped = false;
                }
            }

            // optimisation: If there is not swapping in the last swap, then the array is already sorted.
            if (swapped)
                break;
        }
    }
}