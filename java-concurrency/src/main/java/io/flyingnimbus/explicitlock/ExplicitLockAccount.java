package io.flyingnimbus.explicitlock;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExplicitLockAccount {

    private final Lock lock = new ReentrantLock();

    // to understand the CAS style of AtomicReference, please see AtomicReferenceBalance and its test
    private BigDecimal balance;

    public ExplicitLockAccount(BigDecimal balance) {
        this.balance = balance;
    }

    public void debit(BigDecimal amount) {
        // although the ExplicitLockTransferMoney already would have acquired the lock before doing any mutations, adding
        // the try/finally locking here just for completeness, say this was used in another context.
        lock.lock();
        try {
            balance = balance.subtract(amount);
        } finally {
            lock.unlock();
        }
    }

    public void credit(BigDecimal amount) {
        lock.lock();
        try {
            balance = balance.add(amount);
        } finally {
            lock.unlock();
        }
    }

    public BigDecimal getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

    public Lock getLock() {
        return lock;
    }
}
