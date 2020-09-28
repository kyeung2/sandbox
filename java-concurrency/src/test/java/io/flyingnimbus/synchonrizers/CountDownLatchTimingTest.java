package io.flyingnimbus.synchonrizers;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CountDownLatchTimingTest {

    private final CountDownLatchTiming objectUnderTest = new CountDownLatchTiming();

    @Test
    public void timingWithCountDownLatch() throws InterruptedException {

        Executor executor = Executors.newFixedThreadPool(10);

        Runnable action = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        long nanos = objectUnderTest.timeNanos(executor, action, 10);
        long millis = TimeUnit.NANOSECONDS.toMillis(nanos);

        assertTrue(millis > 1000);
        assertTrue(millis < 1050);
    }

}