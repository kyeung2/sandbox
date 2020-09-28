package io.nimbus.algorithms.sort;


public class HeapSort {

    public static void sort(int[] arr) {
        int n = arr.length;

        // Build max heap. n / 2 - 1 is the first index of a none leaf node, going backwards up to  index 0, the root.
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Heap sort
        for (int i = n - 1; i >= 0; i--) {
            // swap last with root
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Heapify root element
            heapify(arr, i, 0);
        }
    }

   static void heapify(int[] arr, int n, int i) {
        // Find largestIndex among root, left child and right child
        int largestIndex = i;
        int l = 2 * i + 1;// left child index
        int r = 2 * i + 2;// right child index

        if (l < n && arr[l] > arr[largestIndex])
            largestIndex = l;

        if (r < n && arr[r] > arr[largestIndex])
            largestIndex = r;

        // Swap and continue heapifying if root is not largestIndex
        if (largestIndex != i) {
            int swap = arr[i];
            arr[i] = arr[largestIndex];
            arr[largestIndex] = swap;

            heapify(arr, n, largestIndex);
        }
    }
}