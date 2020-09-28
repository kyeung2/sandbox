package io.flyingnimbus.explicitlock;

import io.flyingnimbus.explicitlock.ExplicitLockAccount;
import io.flyingnimbus.explicitlock.ExplicitLockTransferMoney;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.*;

class ExplicitLockTransferMoneyTest {
    private final ExplicitLockTransferMoney objectUnderTest = new ExplicitLockTransferMoney();

    @Test
    public void insufficientFunds() {
        ExplicitLockAccount from = new ExplicitLockAccount(BigDecimal.ZERO);
        ExplicitLockAccount to = new ExplicitLockAccount(BigDecimal.ZERO);

        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class,
                () -> objectUnderTest.transferMoney(from, to, BigDecimal.TEN, 5, SECONDS));

        assertEquals("From account insufficient funds", illegalStateException.getMessage());
    }

    @Test
    public void successfulTransfer() throws InterruptedException {
        ExplicitLockAccount a = new ExplicitLockAccount(BigDecimal.valueOf(50.0));
        ExplicitLockAccount b = new ExplicitLockAccount(BigDecimal.valueOf(10.0));
        ExplicitLockAccount c = new ExplicitLockAccount(BigDecimal.valueOf(40.0));

        objectUnderTest.transferMoney(a, b, BigDecimal.TEN, 5, SECONDS);
        objectUnderTest.transferMoney(b, c, BigDecimal.valueOf(20), 5, SECONDS);

        assertEquals(BigDecimal.valueOf(40.0), a.getBalance());
        assertEquals(BigDecimal.valueOf(0.0), b.getBalance());
        assertEquals(BigDecimal.valueOf(60.0), c.getBalance());
    }

    @Test
    public void noLockOrderingDeadlock() throws InterruptedException {
        ExplicitLockAccount from = new ExplicitLockAccount(BigDecimal.valueOf(50.0));
        ExplicitLockAccount to = new ExplicitLockAccount(BigDecimal.valueOf(50.0));
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        AtomicInteger successes = new AtomicInteger(0);
        AtomicInteger failures = new AtomicInteger(0);
        AtomicInteger insufficientFunds = new AtomicInteger(0);

        for (int i = 0; i < 1_000; i++) {

            executorService.submit(() -> {
                try {
                    if (objectUnderTest.transferMoney(from, to, BigDecimal.TEN, 5, SECONDS)) {
                        successes.incrementAndGet();
                    } else {
                        failures.incrementAndGet();
                    }
                } catch (IllegalStateException e) {
                    insufficientFunds.incrementAndGet();
                } catch (InterruptedException e) {
                    // this shouldn't happen
                }
            });

            executorService.submit(() -> {
                try {
                    if (objectUnderTest.transferMoney(to, from, BigDecimal.TEN, 5, SECONDS)) {
                        successes.incrementAndGet();
                    } else {
                        failures.incrementAndGet();
                    }
                } catch (IllegalStateException e) {
                    insufficientFunds.incrementAndGet();
                } catch (InterruptedException e) {
                    // this shouldn't happen
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(50, TimeUnit.SECONDS);


        assertEquals(BigDecimal.valueOf(100.0), from.getBalance().add(to.getBalance()));  // no new money is created in the system
        assertTrue(successes.get() > 0);
        assertTrue(failures.get() > 0);
        assertEquals(2_000, successes.get() + failures.get() + insufficientFunds.get());// all transactions accounted for
    }

}