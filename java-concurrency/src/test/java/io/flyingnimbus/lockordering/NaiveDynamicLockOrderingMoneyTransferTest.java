package io.flyingnimbus.lockordering;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class NaiveDynamicLockOrderingMoneyTransferTest {

    private NaiveDynamicLockOrderingMoneyTransfer objectUnderTest = new NaiveDynamicLockOrderingMoneyTransfer();

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

        // if this timeout was set to a larger number would still not terminate, deadlock is very likely. Test by setting
        // this to 30 seconds.
        executorService.awaitTermination(5, TimeUnit.SECONDS);

        assertEquals(BigDecimal.valueOf(200.0), from.getBalance().add(to.getBalance()));  // no new money is created in the system
        // this proves that not all transactions completed. I.e. deadlock occurred. 5 seconds is more than enough time for it to have completed.
        assertNotEquals(2_000, successes.get() + insufficientFunds.get());// all transactions accounted for

    }

}