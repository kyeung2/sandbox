package io.flyingnimbus.lockordering;

import java.math.BigDecimal;

/**
 * This example is taken from listing 10.3, Inducing a lock ordering to avoid deadlock, Java Concurrency in Practice.
 */
public class InducedLockOrderingMoneyTransfer {

    private static final Object tieLock = new Object();

    public void transferMoney(Account from, Account to, BigDecimal amount) {

        // assuming the object that represents the Account is always the same instance
        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);

        if (fromHash < toHash) {
            synchronized (from) {
                synchronized (to) {
                    doTransfer(from, to, amount);
                }
            }
        } else if (toHash > fromHash) {
            synchronized (to) {
                synchronized (from) {
                    doTransfer(from, to, amount);
                }
            }
        } else {
            // the tieLock could be a bottleneck, but having a tie would be rare
            synchronized (tieLock) {
                synchronized (from) {
                    synchronized (to) {
                        doTransfer(from, to, amount);
                    }
                }
            }
        }

    }

    private void doTransfer(Account from, Account to, BigDecimal amount) {
        if (from.getBalance().compareTo(amount) < 0) {
            throw new IllegalStateException("From account insufficient funds");
        }
        from.debit(amount);
        to.credit(amount);
    }
}