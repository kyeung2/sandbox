package io.nimbus.algorithms.sort;

import io.nimbus.algorithms.sort.HeapSort;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HeapSortTest {


    @Test
    public void average() {

        int[] arr = {4, 2, 2, 8, 3, 3, 1};

        System.out.println("Initial:\t" + Arrays.toString(arr));
        HeapSort.sort(arr);
        System.out.println("Sorted:\t\t" + Arrays.toString(arr));

        assertArrayEquals(new int[]{1, 2, 2, 3, 3, 4, 8}, arr);
    }

}