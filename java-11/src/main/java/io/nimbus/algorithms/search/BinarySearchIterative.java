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


    //TODO practice below. My implementation was good, though I didn't move +-1 of mid per iteration, which is more efficient.
    //TODO the recursive structure is identical, arguably clearer.

    public static void main(String[] args) {

        int[] sorted = {1, 2, 3, 4, 5, 6, 7};
        int x = 6;
        var binarySearch = new BinarySearchIterative();

        System.out.println(binarySearch.practice_iterative(sorted, x));
        System.out.println(binarySearch.practice_recursive(sorted, x));
    }

    public int practice_iterative(int[] array, int x) {

        int left = 0, mid = 0, right = array.length - 1;

        while (left <= right) {

            mid = left + (right - left) / 2;

            if (array[mid] == x) {
                return mid;
            }
            // if smaller, i.e. to the left.
            else if (x < array[mid]) {
                right = mid - 1;
            }
            // else has to be greater, so to the right
            else {
                left = mid + 1;
            }
        }


        return -1;
    }

    public int practice_recursive(int[] array, int x) {

        return practice_recursive_worker(array, x, 0, array.length - 1);
    }

    public int practice_recursive_worker(int[] array, int x, int left, int right) {


        if (left <= right) {

            int mid = left + (right - left) / 2;
            if (array[mid] == x) {
                return mid;
            }
            // if smaller, i.e. to the left.
            else if (x < array[mid]) {
                return practice_recursive_worker(array, x, left, mid - 1);
            }
            // else has to be greater, so to the right
            else {
                return practice_recursive_worker(array, x, mid + 1, right);
            }
        }


        return -1;
    }

}