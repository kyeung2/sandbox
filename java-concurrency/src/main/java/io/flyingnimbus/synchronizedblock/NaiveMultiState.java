package io.flyingnimbus.synchronizedblock;

public class NaiveMultiState {

    private int counter = 0;
    private int counterDoubled = 0;

    public int getCount() {
        return counter;
    }

    public int getCountDoubled() {
        return counterDoubled;
    }

    public void increment() {
        // this is a compound action, (read, increment, write), therefore not threadsafe
        // also not synchronisation so no guarantee the changes will be visible to other threads
        counter++;
        counterDoubled++;
        counterDoubled++;
    }
}
