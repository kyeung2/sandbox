package io.flyingnimbus.threadsafe;


public class StackConfinement {

    /**
     * all state is confined inside the thread's call stack, a special type of thread-confinement
     */
    public int doWork() {

        int a = 4;
        int b = 9;

        // compound operations
        a++;
        b++;

        return a * b;
    }
}
