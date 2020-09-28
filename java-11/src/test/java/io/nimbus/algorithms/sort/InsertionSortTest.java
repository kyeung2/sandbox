package io.nimbus.algorithms.sort;

import io.nimbus.algorithms.sort.InsertionSort;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class InsertionSortTest {

    @Test
    public void average() {
        int[] arr = {6, 5, 12, 10, 9, 1};
        System.out.println("Initial:\t" + Arrays.toString(arr));

        InsertionSort.sort(arr);
        System.out.println("Sorted:\t\t" + Arrays.toString(arr));

        assertArrayEquals(new int[]{1, 5, 6, 9, 10, 12}, arr);
    }

}