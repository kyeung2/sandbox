package io.flyingnimbus.atomic.integer;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class NaiveCounterTest {

    private final NaiveIntegerCounter objectUnderTest = new NaiveIntegerCounter();

    @Test
    public void safetyFailureWithNaiveCounter() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10_000; i++) {
            executorService.submit(objectUnderTest::increment);
        }
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);

        assertNotEquals(10_000, objectUnderTest.getCount());
    }

}