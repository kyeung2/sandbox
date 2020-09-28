package io.flyingnimbus.synchronizedblock;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class NaiveMultiStateTest {

    private final NaiveMultiState objectUnderTest = new NaiveMultiState();

    @Test
    public void safetyFailureInvariantBroken() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 1_000; i++) {
            executorService.submit(objectUnderTest::increment);
        }
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);

        assertNotEquals(1_000, objectUnderTest.getCount());
        assertNotEquals(2_000, objectUnderTest.getCountDoubled());
        // this test is the most interesting,  obvious that multi-thread calls to increment() would not have all
        // the calls but it also broke the invariant that count-doubled should be 2X of count
        assertNotEquals(objectUnderTest.getCountDoubled(), objectUnderTest.getCount() * 2);
    }

}