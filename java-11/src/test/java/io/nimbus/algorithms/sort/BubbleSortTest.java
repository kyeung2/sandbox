package io.nimbus.algorithms.sort;

import io.nimbus.algorithms.sort.BubbleSort;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class BubbleSortTest {

    @Test
    public void average() {
        int[] arr = {6, 5, 12, 10, 9, 1};
        System.out.println("Initial:\t" + Arrays.toString(arr));

        BubbleSort.sort(arr);
        System.out.println("Sorted:\t\t" + Arrays.toString(arr));

        assertArrayEquals(new int[]{1, 5, 6, 9, 10, 12}, arr);
    }

    // the optimisation means this will have O(n) complexity
    @Test
    public void sorted() {
        int[] arr = {-1, 0, 1, 2, 2, 3, 4, 5, 6};
        System.out.println("Initial:\t" + Arrays.toString(arr));

        BubbleSort.sort(arr);
        System.out.println("Sorted:\t\t" + Arrays.toString(arr));

        assertArrayEquals(new int[]{-1, 0, 1, 2, 2, 3, 4, 5, 6}, arr);
    }
}