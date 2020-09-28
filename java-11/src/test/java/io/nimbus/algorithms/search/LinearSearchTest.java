package io.nimbus.algorithms.search;

import io.nimbus.algorithms.search.LinearSearch;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinearSearchTest {

    @Test
    public void found() {
        int[] arr = {6, 5, 12, 10, 9, 1};
        int x = 10;

        int index = LinearSearch.linearSearch(arr, x);

        assertEquals(3, index);
    }

    @Test
    public void notFound() {
        int[] arr = {6, 5, 12, 10, 9, 1};
        int x = 11;

        int index = LinearSearch.linearSearch(arr, x);

        assertEquals(-1, index);
    }
}