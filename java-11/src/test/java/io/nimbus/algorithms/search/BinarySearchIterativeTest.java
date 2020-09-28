package io.nimbus.algorithms.search;

import io.nimbus.algorithms.search.BinarySearchIterative;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySearchIterativeTest {

    private final int[] sortedArray = {1, 2, 3, 4, 5};

    @Test
    public void found() {
        int x = 4;

        int index = BinarySearchIterative.binarySearch(sortedArray, x);

        assertEquals(3, index);
    }

    @Test
    public void notFound() {
        int x = 11;

        int index = BinarySearchIterative.binarySearch(sortedArray, x);

        assertEquals(-1, index);
    }
}