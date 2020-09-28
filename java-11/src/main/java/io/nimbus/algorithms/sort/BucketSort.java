package io.nimbus.algorithms.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BucketSort {

    public static void sort(double[] arr, int n) {
        if (n <= 0)
            return;

        @SuppressWarnings("unchecked")
        List<Double>[] bucket = new ArrayList[n];

        // Create empty buckets
        for (int i = 0; i < n; i++)
            bucket[i] = new ArrayList<>();

        // Add elements into the buckets
        for (int i = 0; i < n; i++) {
            int bucketIndex = (int) arr[i] * n;
            bucket[bucketIndex].add(arr[i]);
        }

        // Sort the elements of each bucket
        for (int i = 0; i < n; i++) {
            Collections.sort((bucket[i]));
        }

        // Get the sorted array
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0, size = bucket[i].size(); j < size; j++) {
                arr[index++] = bucket[i].get(j);
            }
        }
    }

}