package io.flyingnimbus.atomic.integer;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AtomicIntegerCounterTest {

    private final AtomicIntegerCounter objectUnderTest = new AtomicIntegerCounter();

    @Test
    public void concurrentIncrementOnAtomicCounter() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10_000; i++) {
            executorService.submit(objectUnderTest::increment);
        }

        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);

        assertEquals(10_000, objectUnderTest.getCount());
    }

}