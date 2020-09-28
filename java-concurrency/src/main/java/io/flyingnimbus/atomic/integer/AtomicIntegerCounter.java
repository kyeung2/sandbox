package io.flyingnimbus.atomic.integer;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerCounter {

    private final AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        // is the atomic version of int counter++
        counter.getAndIncrement();
    }

    public int getCount() {
        return counter.get();
    }
}
