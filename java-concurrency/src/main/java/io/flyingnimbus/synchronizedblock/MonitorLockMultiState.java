package io.flyingnimbus.synchronizedblock;

/**
 * instance methods with synchronized uses this instance's intrinsic/monitor lock.
 */
public class MonitorLockMultiState {

    // share mutable state requires synchronisation to be thread-safe, otherwise clients would have to do it externally
    // the invariant of this class is that the counterDoubled should always be 2X of counter.
    private int counter = 0;
    private int counterDoubled = 0;

    /*
     using this object's intrinsic/monitor lock.
     this is a trivial example but the point is, the entire block/method is now atomic, and once exited, visible
     */
    public synchronized int getCount() {
        return counter;
    }

    public synchronized int getCountDoubled() {
        return counterDoubled;
    }

    public synchronized void increment() {
        // this is a compound action, (read, increment, write), therefore not threadsafe
        counter++;
        counterDoubled++;
        counterDoubled++;
    }

}
