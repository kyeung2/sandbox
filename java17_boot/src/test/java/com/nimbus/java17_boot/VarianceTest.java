package com.nimbus.java17_boot;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.Future;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class VarianceTest {


    public static double sum(Collection<? extends Number> numbers) {
        double result = 0.0;

        for (Number num : numbers) result += num.doubleValue();

        return result;
    }

    @Test
    void invariantTypes(){


        Optional.empty().map()
        List<Integer> integers = Arrays.asList(2, 4, 6);
        // this would have been a compile-time error
        double sum = sum(integers);
        System.out.println("Sum of integers = " + sum);

        List<Double> doubles = Arrays.asList(3.14, 1.68, 2.94);
        // this would also have been a compile-time error
        sum = sum(doubles);
        System.out.println("Sum of doubles = " + sum);

        List<Number> numbers = Arrays.<Number>asList(2, 4, 6, 3.14, 1.68, 2.94);
        sum = sum(numbers);
        System.out.println("Sum of numbers = " + sum);

    }
}
