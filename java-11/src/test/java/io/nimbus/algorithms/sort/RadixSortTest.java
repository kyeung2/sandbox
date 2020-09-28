package io.nimbus.algorithms.sort;

import io.nimbus.algorithms.sort.RadixSort;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class RadixSortTest {

    @Test
    public void average() {
        int[] arr = {121, 432, 564, 23, 1, 45, 788};
        System.out.println("Initial:\t" + Arrays.toString(arr));

        RadixSort.sort(arr);
        System.out.println("Sorted:\t\t" + Arrays.toString(arr));

        assertArrayEquals(new int[]{1, 23, 45, 121, 432, 564, 788}, arr);
    }

}