package io.flyingnimbus.threadsafe;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StackConfinementTest {

    private final StackConfinement objectUnderTest = new StackConfinement();

    @Test
    public void otherThreadsCannotInterfere() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        AtomicInteger correctResult = new AtomicInteger(0);
        for (int i = 0; i < 1_000; i++) {
            executorService.submit(() -> {

                // irrespective of the level of concurrency, it will have no impact
                if (objectUnderTest.doWork() == 50) {
                    correctResult.getAndIncrement();
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);

        assertEquals(1_000, correctResult.get());
    }

}