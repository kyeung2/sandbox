package io.flyingnimbus.synchronizedblock;

/**
 * potential advantages are, more granular control, choice of lock object. e.g. Some concurrent
 * collections use lock stripping (use of multiple lock objects) to reduce contention
 */
public class SynchronizedBlockMultiState {

    private int counter = 0;
    private int counterDoubled = 0;

    public int getCount() {

        // this is very equivalent to the MonitorLockMultiState example.
        synchronized (this) {
            return counter;
        }
    }

    public int getCountDoubled() {
        synchronized (this) {
            return counterDoubled;
        }
    }

    public void increment() {

        synchronized (this) {
            // these are all compound actions
            counter++;
            counterDoubled++;
            counterDoubled++;
        }

    }

}
