package io.flyingnimbus.atomic.integer;


public class NaiveIntegerCounter {

    private int counter;

    public void increment() {
        // this is a compound action, (read, increment, write), therefore not threadsafe
        counter++;
    }
    public int getCount() {
        return counter;
    }
}
