package io.flyingnimbus.synchronizedblock;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class MonitorLockMultiStateTest {

    private final MonitorLockMultiState objectUnderTest = new MonitorLockMultiState();

    @Test
    public void invariantMaintainedThroughSynchronisation() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1_000; i++) {
            executorService.submit(objectUnderTest::increment);
        }
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);

        assertEquals(1_000, objectUnderTest.getCount());
        assertEquals(2_000, objectUnderTest.getCountDoubled());
        assertEquals(objectUnderTest.getCountDoubled(), objectUnderTest.getCount() * 2);
    }

}