package io.flyingnimbus.atomic.reference;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceBalance {

    private final AtomicReference<BigDecimal> balanceReference = new AtomicReference<>(BigDecimal.ZERO);

    public boolean add(BigDecimal value) {

        BigDecimal currentBalance = balanceReference.get();
        BigDecimal newBalance = currentBalance.add(value);
        // interesting this is a form of optimistic concurrency control OCC
        // CAS = compare and swap
        return balanceReference.compareAndSet(currentBalance, newBalance);
    }

    public BigDecimal getBalance() {
        return balanceReference.get();
    }
}
