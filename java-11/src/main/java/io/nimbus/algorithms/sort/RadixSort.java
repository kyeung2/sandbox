package io.nimbus.algorithms.sort;

/**
 * taken from https://www.programiz.com/dsa/radix-sort
 */
public class RadixSort {

    // Using counting sort to sort the elements in the basis of significant places
    static void countingSort(int[] array, int size, int place) {
        int[] output = new int[size + 1];
        int max = getMax(array);
        int[] count = new int[max + 1];

        // Calculate count of elements
        for (int i = 0; i < size; i++)
            count[(array[i] / place) % 10]++;

        // Calculate cumulative count
        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Place the elements in sorted order
        for (int i = size - 1; i >= 0; i--) {
            output[count[(array[i] / place) % 10] - 1] = array[i];
            count[(array[i] / place) % 10]--;
        }

        for (int i = 0; i < size; i++)
            array[i] = output[i];
        //         if (size >= 0) System.arraycopy(output, 0, array, 0, size);
    }

    // Function to get the largest element from an array
    static int getMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++)
            if (array[i] > max)
                max = array[i];
        return max; // OR IntStream.of(array).max().getAsInt();
    }

    public static void sort(int[] array) {
        // Get maximum element
        int max = getMax(array);

        // Apply counting sort to sort elements based on place value.
        for (int place = 1; max / place > 0; place *= 10)
            countingSort(array, array.length, place);
    }
}