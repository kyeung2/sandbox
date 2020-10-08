package io.flyingnimbus.completablefuture;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class CompletableFutureExampleTest {

    private final CompletableFutureExample objectUnderTest = new CompletableFutureExample();

    @Test
    public void runAsyncCommonForkJoinPool() {
        objectUnderTest.runAsyncCommonForkJoinPool();
    }

    @Test
    public void runAsyncExecutor() {
        Executor executor = Executors.newSingleThreadExecutor();
        objectUnderTest.runAsyncExecutor(executor);
    }

    @Test
    public void callbacks() {
        objectUnderTest.callbacks();
    }

    @Test
    public void chainingCompletionStages() throws InterruptedException {
        objectUnderTest.chainingCompletionStages();

        TimeUnit.SECONDS.sleep(2);
    }

    @Test
    public void exceptions() throws ExecutionException, InterruptedException {
        objectUnderTest.exceptions();
    }

    @Test
    public void timeouts() throws ExecutionException, InterruptedException {
        objectUnderTest.timeouts();
    }
}