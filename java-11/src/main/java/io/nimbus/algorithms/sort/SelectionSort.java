package io.nimbus.algorithms.sort;

/**
 * taken from https://www.programiz.com/dsa/selection-sort
 */
public class SelectionSort {

    public static void sort(int[] array) {
        int size = array.length;

        for (int step = 0; step < size - 1; step++) {
            int min_idx = step;

            for (int i = step + 1; i < size; i++) {

                // To sort in descending order, change > to < in this line.
                // "SELECT" the minimum element in each loop.
                if (array[i] < array[min_idx]) {
                    min_idx = i;
                }
            }

            // swap selected min in the next position
            int temp = array[step];
            array[step] = array[min_idx];
            array[min_idx] = temp;
        }
    }

}
