package io.flyingnimbus.lockordering;

import java.math.BigDecimal;

/**
 * This example is taken from listing 10.2, Java Concurrency in Practice.
 */
public class NaiveDynamicLockOrderingMoneyTransfer {

    public void transferMoney(Account from, Account to, BigDecimal amount) {

        // dynamic lock-ordering, deadlock-prone
        synchronized (from) {
            synchronized (to) {

                if (from.getBalance().compareTo(amount) < 0) {
                    throw new IllegalStateException("From account insufficient funds");
                }

                from.debit(amount);
                to.credit(amount);
            }
        }
    }
}