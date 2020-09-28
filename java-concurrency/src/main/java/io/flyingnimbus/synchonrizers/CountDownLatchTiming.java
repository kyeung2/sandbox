package io.flyingnimbus.synchonrizers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;


/**
 * A one time use synchronizer, that once countDown() reaches zero, blocked threads are released from the await() point.
 *
 * Example from Item 81, page 327, Effective Java Third Edition
 */
public class CountDownLatchTiming {

    public  long timeNanos(Executor executor, Runnable action, int concurrency) throws InterruptedException {

        CountDownLatch ready = new CountDownLatch(concurrency);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(concurrency);

        for (int i = 0; i < concurrency; i++) {
            executor.execute(() -> {
                ready.countDown();
                try {
                    start.await();
                    action.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    done.countDown();
                }
            });

        }

        ready.await();// will not proceed until all tasks are ready/counted down
        long startNanos = System.nanoTime();
        start.countDown();
        done.await();// will not proceed/blocks until all workers are done
        return System.nanoTime() - startNanos;
    }
}
