package io.nimbus.algorithms.search;

import io.nimbus.algorithms.search.BinarySearchRecursive;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchRecursiveTest {

    private final int[] sortedArray = {1, 2, 3, 4, 5};

    @Test
    public void found() {
        int x = 4;

        int index = BinarySearchRecursive.binarySearch(sortedArray, x);

        assertEquals(3, index);
    }

    @Test
    public void notFound() {
        int x = 11;

        int index = BinarySearchRecursive.binarySearch(sortedArray, x);

        assertEquals(-1, index);
    }
}