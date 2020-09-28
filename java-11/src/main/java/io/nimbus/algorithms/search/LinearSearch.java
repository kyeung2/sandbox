package io.nimbus.algorithms.search;

/**
 * taken from https://www.programiz.com/dsa/linear-search
 */
public class LinearSearch {

    public static int linearSearch(int[] array, int x) {
        int n = array.length;

        // Going through array sequentially
        for (int i = 0; i < n; i++) {
            if (array[i] == x)
                return i;
        }
        return -1;
    }
}