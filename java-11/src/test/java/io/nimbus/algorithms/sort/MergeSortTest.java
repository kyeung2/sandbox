package io.nimbus.algorithms.sort;

import io.nimbus.algorithms.sort.MergeSort;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MergeSortTest {

    @Test
    public void empty() {
        int[] arr = {};

        MergeSort.sort(arr);

        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    public void single() {
        int[] arr = {9};

        MergeSort.sort(arr);

        assertArrayEquals(new int[]{9}, arr);
    }

    @Test
    public void pair() {
        int[] arr = {9, 8};

        MergeSort.sort(arr);

        assertArrayEquals(new int[]{8, 9}, arr);
    }

    @Test
    public void sorted() {
        int[] arr = {-1, 0, 1, 2, 2, 3, 4, 5, 6};
        System.out.println("Initial:\t" + Arrays.toString(arr));

        MergeSort.sort(arr);
        System.out.println("Sorted:\t\t" + Arrays.toString(arr));

        assertArrayEquals(new int[]{-1, 0, 1, 2, 2, 3, 4, 5, 6}, arr);
    }

    @Test
    public void average() {
        int[] arr = {6, 5, 12, 10, 9, 1};
        System.out.println("Initial:\t" + Arrays.toString(arr));

        MergeSort.sort(arr);
        System.out.println("Sorted:\t\t" + Arrays.toString(arr));

        assertArrayEquals(new int[]{1, 5, 6, 9, 10, 12}, arr);
    }
}