package io.flyingnimbus.volatiles;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VolatileFlagTest {

    // difficult to prove the a none volatile flag version will fail, reliably, in a unit test.
    private final VolatileFlag objectUnderTest = new VolatileFlag();

    @Test
    public void volatileStateVisible() throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(objectUnderTest::doWork);
        TimeUnit.MILLISECONDS.sleep(100);
        int workDone = objectUnderTest.getCounter();

        // send signal via volatile flag to stop to cooperating threads
        objectUnderTest.cancel();

        TimeUnit.MILLISECONDS.sleep(100);
        int workDoneAfterCancelled = objectUnderTest.getCounter();
        TimeUnit.MILLISECONDS.sleep(100);
        int noMoreWorkDone = objectUnderTest.getCounter();
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);

        assertTrue(workDone > 0);// we did some work
        assertTrue(workDone < workDoneAfterCancelled); // we did some more work
        assertEquals(workDoneAfterCancelled, noMoreWorkDone); // after cancellation, no more work
    }
}