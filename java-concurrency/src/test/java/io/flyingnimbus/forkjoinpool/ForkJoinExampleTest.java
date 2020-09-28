package io.flyingnimbus.forkjoinpool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import static org.junit.jupiter.api.Assertions.*;

class ForkJoinExampleTest {

    @Test
    public  void fib() {

        final int numberOfProcessors = Runtime.getRuntime().availableProcessors();
        final ForkJoinPool forkJoinPool = new ForkJoinPool(numberOfProcessors);

        final ForkJoinTask<Integer> result = forkJoinPool.submit(new ForkJoinExample.Fibonacci(6));

        assertEquals(8, result.join());
    }
}