package io.nimbus.algorithms.sort;

public class InsertionSort {

    public static void sort(int[] array) {

        for (int step = 1; step < array.length; step++) {
            int value = array[step];
            int j = step - 1;

            // Compare value with each element on the left of it until an element smaller than
            // it is found.
            // For descending order, change value<array[j] to value>array[j].
            while (j >= 0 && value < array[j]) {
                array[j + 1] = array[j];
                --j;
            }

            // Place value at after the element just smaller than it.
            array[j + 1] = value;
        }
    }
}