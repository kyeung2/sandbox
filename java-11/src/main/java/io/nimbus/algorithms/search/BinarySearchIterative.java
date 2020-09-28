package io.nimbus.algorithms.search;

/**
 * taken from https://www.programiz.com/dsa/binary-search
 */
public class BinarySearchIterative {


    // reassigning parameters, not the nicest in the world, but just want to see the algorithm
    static int binarySearch(int[] array, int x, int low, int high) {

        // Repeat until the pointers low and high meet each other
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (array[mid] == x)
                return mid;

            // search right half
            if (array[mid] < x)
                low = mid + 1;
            // search left half
            else
                high = mid - 1;
        }

        return -1;
    }

    public static int binarySearch(int[] array, int x) {
        return binarySearch(array, x, 0, array.length - 1);
    }

}