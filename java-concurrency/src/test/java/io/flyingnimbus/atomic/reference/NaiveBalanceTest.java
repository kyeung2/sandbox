package io.flyingnimbus.atomic.reference;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class NaiveBalanceTest {

    private final NaiveBalance objectUnderTest = new NaiveBalance();

    @Test
    public void safetyFailureWithNaiveBalance() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 1_000; i++) {
            executorService.submit(() -> objectUnderTest.add(BigDecimal.ONE));
        }
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);

        assertNotEquals(1_000, objectUnderTest.getBalance().intValue());
    }

}