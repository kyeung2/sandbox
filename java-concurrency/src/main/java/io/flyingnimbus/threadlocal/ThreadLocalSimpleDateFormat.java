package io.flyingnimbus.threadlocal;

import java.text.SimpleDateFormat;

/**
 * A common use of thread local is for thread unsafe objects such as SimpleDateFormat. Quote from docs: https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
 * <p>
 * ...It is recommended to create separate format instances for each thread. If multiple threads access a format concurrently, it must be synchronized externally.
 * <p>
 */
public class ThreadLocalSimpleDateFormat {

    // ThreadLocal's are usually private static, as per documentation: https://docs.oracle.com/javase/7/docs/api/java/lang/ThreadLocal.html
    // ThreadLocal ensures thread-confinement
    private static final ThreadLocal<SimpleDateFormat> localDateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));

    public SimpleDateFormat getFormat() {
        // first time this is called for a Thread, it will call setInitialValue() and add result to a map.
        return localDateFormat.get();
    }
}
