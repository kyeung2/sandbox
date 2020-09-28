package io.nimbus.algorithms.search;

/**
 * taken from https://www.programiz.com/dsa/binary-search
 */
public class BinarySearchRecursive {

    static int binarySearch(int[] array, int x, int low, int high) {

        if (high >= low) {
            int mid = low + (high - low) / 2;

            // If found at mid, then return it
            if (array[mid] == x)
                return mid;
            // Search the left half
            if (array[mid] > x)
                return binarySearch(array, x, low, mid - 1);

            // Search the right half
            return binarySearch(array, x, mid + 1, high);
        }

        return -1;
    }

    public static int binarySearch(int[] array, int x) {
        return binarySearch(array, x, 0, array.length - 1);
    }

}