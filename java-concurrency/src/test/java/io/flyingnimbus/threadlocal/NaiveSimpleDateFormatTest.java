package io.flyingnimbus.threadlocal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class NaiveSimpleDateFormatTest {
    private final NaiveSimpleDateFormat objectUnderTest = new NaiveSimpleDateFormat();

    @Test
    public void concurrentAccessToUnsafeSimpleDateFormat() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        String dateStr = "2018-06-22T10:00:00";
        AtomicInteger successfulInvocations = new AtomicInteger(0);
        AtomicInteger failureInvocations = new AtomicInteger(0);

        // 10 threads at a time calling getFormat(), which is the same instance of SimpleDateFormat
        for (int i = 0; i < 1_000; i++) {
            executorService.submit(() -> {
                try {
                    objectUnderTest.getFormat().parse(dateStr);
                    successfulInvocations.getAndIncrement();
                } catch (Exception e) {
                    failureInvocations.getAndIncrement();
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);

        assertEquals(1_000, successfulInvocations.get() + failureInvocations.get());
        assertTrue(successfulInvocations.get() < 1_000);
        assertTrue(failureInvocations.get() > 0);
    }

}