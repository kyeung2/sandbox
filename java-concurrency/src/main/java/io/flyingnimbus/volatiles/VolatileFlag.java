package io.flyingnimbus.volatiles;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileFlag {

    private volatile boolean cancelled;

    private final AtomicInteger counter = new AtomicInteger(0);

    public void doWork() {
        while (!cancelled) {
            // do some work while not cancelled
            counter.getAndIncrement();

        }
    }

    public int getCounter() {
        return counter.get();
    }


    public void cancel() {
        // this operation is ATOMIC, and since the variable is volatile, will be immediately visible to all threads
        cancelled = true;
    }

}
