package io.flyingnimbus.atomic.reference;

import java.math.BigDecimal;

public class NaiveBalance {
    // having a direct reference to the immutable BigDecimal object, cannot be final
    private BigDecimal balance = BigDecimal.ZERO;

    // would require a mutex on the setter and accessors to make it thread safe / pessimistic locking
    public void add(BigDecimal value) {
        balance = balance.add(value);
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
