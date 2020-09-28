package io.flyingnimbus.explicitlock;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Explicit locks offer different types of lock acquisition
 * - unconditional: lock(), same as intrinsic locks
 * - polled: tryLock(), offers probabilistic deadlock avoidance
 * - timed: tryLock(long time, TimeUnit unit), offers probabilistic deadlock avoidance
 * - interruptible: lockInterruptibly()
 * <p>
 * Can specify fairness (unfair by default), but costs. Barging is often good enough.
 * <p>
 * This example is taken from Listing 13.3 Avoiding lock-ordering deadlock using tryLock(), Java Concurrency in Practice
 * (with minor adjustments)
 */
public class ExplicitLockTransferMoney {

    /**
     * @return - false if timed out
     * @throws InterruptedException - due to TimeUnit.sleep()
     */
    public boolean transferMoney(ExplicitLockAccount from, ExplicitLockAccount to, BigDecimal amount, long timoutDuration, TimeUnit timeoutUnit) throws InterruptedException {

        // Arbitrarily proportion of time budget, depends on the business domain.
        long fixedDelay = timeoutUnit.toNanos(timoutDuration) / 5;
        // A small proportion of the time budget used as the modulo of the random component
        long randomMod = timeoutUnit.toNanos(timoutDuration) / 10;
        long stopTimeNanos = System.nanoTime() + timeoutUnit.toNanos(timoutDuration);

        // this example uses a retry strategy given a timeout budget
        while (true) {

            // lock ordering deadlocking not an issue with a polled lock acquisition.
            if (from.getLock().tryLock()) {
                try {

                    if (to.getLock().tryLock()) {
                        try {

                            if (from.getBalance().compareTo(amount) < 0) {
                                throw new IllegalStateException("From account insufficient funds");
                            }

                            from.debit(amount);
                            to.credit(amount);
                            return true;

                        } finally {
                            to.getLock().unlock();
                        }
                    }
                } finally {
                    from.getLock().unlock();
                }
            }

            // past time limit, give up on transaction
            if (System.nanoTime() < stopTimeNanos) {
                return false;
            }
            // if we couldn't obtain both locks, poll again, after a delay, with a random element to avoid liveness risk.
            TimeUnit.NANOSECONDS.sleep(fixedDelay + ThreadLocalRandom.current().nextLong() % randomMod);
        }
    }

}
