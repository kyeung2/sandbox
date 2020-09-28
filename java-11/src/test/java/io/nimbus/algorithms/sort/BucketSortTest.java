package io.nimbus.algorithms.sort;

import io.nimbus.algorithms.sort.BucketSort;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class BucketSortTest {

    @Test
    public void average() {

        double[] arr = {0.42, 0.32, 0.33, 0.52, 0.37, 0.47, 0.51};

        System.out.println("Initial:\t" + Arrays.toString(arr));
        BucketSort.sort(arr, 7);
        System.out.println("Sorted:\t\t" + Arrays.toString(arr));

        assertArrayEquals(new double[]{0.32, 0.33, 0.37, 0.42, 0.47, 0.51, 0.52}, arr);
    }

}