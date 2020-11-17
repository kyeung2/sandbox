package io.flyingnimbus.completablefuture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

class CompletableFutureExampleTest {

    private final CompletableFutureExample objectUnderTest = new CompletableFutureExample();

    @Test
    void runAsyncCommonForkJoinPool() {
        objectUnderTest.runAsyncCommonForkJoinPool();
    }

    @Test
    void runAsyncExecutor() {
        Executor executor = Executors.newSingleThreadExecutor();
        objectUnderTest.runAsyncExecutor(executor);
    }

    @Test
    void callbacks() {
        objectUnderTest.callbacks();
    }

    @Test
    void chainingCompletionStages() throws InterruptedException {
        objectUnderTest.chainingCompletionStages();

        sleep(2);
    }

    @Test
    void exceptions() throws ExecutionException, InterruptedException {
        objectUnderTest.exceptions();
    }

    @Test
    void timeouts() throws ExecutionException, InterruptedException {
        objectUnderTest.timeouts();
    }

    @Test
    void supplyAsync() {

        System.out.println("execution 1: thread: " + Thread.currentThread().getName());

        CompletableFuture<String> job1 = CompletableFuture.supplyAsync(() -> {

            sleep(1);
            System.out.println("supplyAsync: thread: " + Thread.currentThread().getName());
            return "Job 1";
        });

        System.out.println("execution 2: thread: " + Thread.currentThread().getName());

        job1.join();
    }

    @Test
    void thenAcceptBothAsync() {

        System.out.println("execution 1: thread: " + Thread.currentThread().getName());

        CompletableFuture<String> job1 = CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i < 6; i++) {
                sleep(250);
                System.out.println("supplyAsync1: thread: " + Thread.currentThread().getName());
            }
            return "Job 1";
        });

        CompletableFuture<String> job2 = CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i < 4; i++) {
                sleep(250);
                System.out.println("supplyAsync2: thread: " + Thread.currentThread().getName());
            }
            return "Job 2";
        });

        System.out.println("execution 2: thread: " + Thread.currentThread().getName());

        //NOTE: using either thenAcceptBothAsync pr thenAcceptBoth would have the same  "async"effect as job1 and job2
        // already use supplyAsync()
        job1.thenAcceptBothAsync(job2, (job1Result, job2Result) -> {
            System.out.println("thenAcceptBothAsync: thread: " + Thread.currentThread().getName());
            System.out.println(job1Result);
            System.out.println(job2Result);
        }).join();

        job1.join();

        System.out.println("execution 3: thread: " + Thread.currentThread().getName());
    }


    private void sleep(int i) {
        try {
            TimeUnit.MILLISECONDS.sleep(i);
        } catch (InterruptedException e) {
            Assertions.fail("this shouldn't have happened", e);
        }
    }
}

