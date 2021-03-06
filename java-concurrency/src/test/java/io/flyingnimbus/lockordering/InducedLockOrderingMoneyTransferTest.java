package io.flyingnimbus.lockordering;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class InducedLockOrderingMoneyTransferTest {

    private InducedLockOrderingMoneyTransfer objectUnderTest = new InducedLockOrderingMoneyTransfer();

    @Test
    public void noDeadlockingDueToDynamicOrdering() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Account from = new Account(BigDecimal.valueOf(100.0));
        Account to = new Account(BigDecimal.valueOf(100.0));
        AtomicInteger successes = new AtomicInteger(0);
        AtomicInteger insufficientFunds = new AtomicInteger(0);

        for (int i = 0; i < 1_000; i++) {

            executorService.submit(() -> {
                try {
                    objectUnderTest.transferMoney(from, to, BigDecimal.TEN);
                    successes.incrementAndGet();
                } catch (IllegalStateException e) {
                    insufficientFunds.incrementAndGet();
                }
            });
            executorService.submit(() -> {
                try {
                    // swapping the direction of the accounts. Without the induced lock ordering, this would most likely deadlock.
                    objectUnderTest.transferMoney(to, from, BigDecimal.TEN);
                    successes.incrementAndGet();
                } catch (IllegalStateException e) {
                    insufficientFunds.incrementAndGet();
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(50, TimeUnit.SECONDS);

        assertEquals(BigDecimal.valueOf(200.0), from.getBalance().add(to.getBalance()));  // no new money is created in the system
        assertEquals(2_000, successes.get() + insufficientFunds.get());// all transactions accounted for

    }

}