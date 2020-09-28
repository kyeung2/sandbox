package io.nimbus.algorithms.sort;


import java.util.Arrays;

/**
 * Taken from https://www.programiz.com/dsa/merge-sort
 * got to love the variable naming convention of algorithm people :)
 */
public class MergeSort {

    // Merge two subarrays L and M into arr
    static void merge(int[] arr, int p, int q, int r) {

        // Create L ← A[p..q] and M ← A[q+1..r]
        int n1 = q - p + 1;
        int n2 = r - q;

        int[] L = new int[n1];
        int[] M = new int[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[p + i];
        for (int j = 0; j < n2; j++)
            M[j] = arr[q + 1 + j];

        // Maintain current index of sub-arrays and main array
        int i, j, k;
        i = 0;
        j = 0;
        k = p;

        // Until we reach either end of either L or M, pick larger among
        // elements L and M and place them in the correct position at A[p..r]
        while (i < n1 && j < n2) {
            if (L[i] <= M[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = M[j];
                j++;
            }
            k++;
        }

        // When we run out of elements in either L or M,
        // pick up the remaining elements and put in A[p..r]
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = M[j];
            j++;
            k++;
        }
    }


    // Divide the array into two sub arrays, sort them and merge them
    private static void sort(int[] arr, int l, int r) {
        
        if (l < r) {

            // m is the point where the array is divided into two sub arrays
            int m = (l + r) / 2;

            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Merge the sorted sub arrays
            merge(arr, l, m, r);
        }
    }

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }


    /**
     * A pretty intuitive implementation using Arrays.copyRange()
     * https://leetcode.com/explore/learn/card/recursion-ii/470/divide-and-conquer/2868/
     */
    static class TopDownRecursivePureFunction {
        public int[] sort(int[] nums) {
            return mergeSort_topdown(nums);
        }

        public int[] mergeSort_topdown(int[] input) {

            if (input.length <= 1)
                return input;

            int mid = input.length / 2;
            int[] left = mergeSort_topdown(Arrays.copyOfRange(input, 0, mid));
            int[] right = mergeSort_topdown(Arrays.copyOfRange(input, mid, input.length));
            return merge_topdown(left, right);
        }

        private int[] merge_topdown(int[] left, int[] right) {
            int[] ret = new int[left.length + right.length];
            int retIndex = 0, leftIndex = 0, rightIndex = 0;

            while (leftIndex < left.length && rightIndex < right.length) {
                if (left[leftIndex] < right[rightIndex])
                    ret[retIndex++] = left[leftIndex++];
                else
                    ret[retIndex++] = right[rightIndex++];
            }
            // copy over the remaining
            while (leftIndex < left.length) {
                ret[retIndex++] = left[leftIndex++];
            }
            while (rightIndex < right.length) {
                ret[retIndex++] = right[rightIndex++];
            }
            return ret;
        }
    }

}