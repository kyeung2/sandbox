package io.nimbus.algorithms.sort;

import java.util.Arrays;

public class Practices {


    public static void main(String[] args) {

        var practice = new Practices();

        // binary search
        {
            int[] arr = {1, 2, 3, 4, 5, 6, 7, 7, 9};
            int x = 1;
            System.out.println(practice.binarySearch(arr, x, 0, arr.length - 1));
        }

        // merge sort
        {
            int[] arr = {5, 7, 3, 5, 6, 9, 5, 1, 1, 1};
            practice.mergeSort(arr);
            System.out.println(Arrays.toString(arr));
        }

    }

    int binarySearch(int[] arr, int x, int l, int r) {

        // TODO this tripped me up it is a <=
        if (l <= r) {
            int mid = l + (r - l) / 2;

            if (arr[mid] == x) {
                return x;
            } else if (arr[mid] > x) { // IF WE ARE LARGER THAN X IS SMALLER
                return binarySearch(arr, x, l, mid - 1);
            }

            return binarySearch(arr, x, mid + 1, r);

        }

        return -1;
    }

    void mergeSort(int[] arr) {
        divide(arr, 0, arr.length - 1);
    }

    void divide(int[] arr, int l, int r) {
        if (l<r) {

            int mid = l + (r - l) / 2;
            divide(arr, l, mid);
            divide(arr, mid + 1, r);
            conquer(arr, l, mid, r);
        }
    }

    private void conquer(int[] arr, int l, int m, int r) {
        // fill in l and r sub-arrays
        int[] L = new int[m - l + 1];
        int[] R = new int[r - m];

        for (int i = 0; i < L.length; i++) {
            L[i] = arr[l + i];
        }
        for (int i = 0; i < R.length; i++) {
            R[i] = arr[m + 1 + i];
        }

        int mainIndex = l;
        int lIndex = 0;
        int rIndex = 0;
        // fill in arr

        while (lIndex < L.length  && rIndex < R.length ) {
            if (L[lIndex] <= R[rIndex]) {
                arr[mainIndex++] = L[lIndex++];
            } else {
                arr[mainIndex++] = R[rIndex++];
            }
        }
        // fill in remaining l
        while (lIndex < L.length ) {
            arr[mainIndex++] = L[lIndex++];
        }

        // fill in remaining r
        while (rIndex < R.length ) {
            arr[mainIndex++] = R[rIndex++];
        }
    }
}
