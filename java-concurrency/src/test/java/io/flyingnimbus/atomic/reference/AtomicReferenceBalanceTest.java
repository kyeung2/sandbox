package io.flyingnimbus.atomic.reference;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AtomicReferenceBalanceTest {

    private final AtomicReferenceBalance objectUnderTest = new AtomicReferenceBalance();

    // OCC == Optimistic Concurrency Control
    @Test
    public void occViaAtomicReferenceCompareAndSet() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        AtomicInteger lockFailureCount = new AtomicInteger(0);
        for (int i = 0; i < 1_000; i++) {
            executorService.submit(() -> {
                if (!objectUnderTest.add(BigDecimal.ONE)) {

                    // this technique could be used to inform the caller of failure or allow the system to retry etc
                    lockFailureCount.getAndIncrement();
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);

        assertTrue(lockFailureCount.get() > 0);
        // successfully captured each failure instance
        assertEquals(1_000, objectUnderTest.getBalance().intValue() + lockFailureCount.get());
    }
}