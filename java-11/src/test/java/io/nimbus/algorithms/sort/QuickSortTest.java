package io.nimbus.algorithms.sort;

import io.nimbus.algorithms.sort.QuickSort;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class QuickSortTest {

    @Test
    public void average() {

        int[] arr = {8, 7, 6, 1, 0, 9, 2};

        System.out.println("Initial:\t" + Arrays.toString(arr));
        QuickSort.sort(arr);
        System.out.println("Sorted:\t\t" + Arrays.toString(arr));

        assertArrayEquals(new int[]{0, 1, 2, 6, 7, 8, 9}, arr);
    }
}