package io.flyingnimbus.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import static io.flyingnimbus.completablefuture.FakeFactory.*;

/**
 * Taking examples from https://codeburst.io/a-new-concurrency-model-in-java-975d597dd5e4
 */
public class CompletableFutureExample {

    // uses common ForkJoinPool
    public void runAsyncCommonForkJoinPool() {
        Runnable task = () ->
                System.out.println("Async task thread : " + Thread.currentThread().getName());

        final CompletableFuture<Void> future = CompletableFuture.runAsync(task);

        System.out.println("Main thread : " + Thread.currentThread().getName());
        future.join();
    }

    // uses common ForkJoinPool
    public void runAsyncExecutor(Executor executor) {
        Runnable task = () ->
                System.out.println(getCurrentThread() + "Async task thread");

        final CompletableFuture<Void> future = CompletableFuture.runAsync(task, executor);

        System.out.println(getCurrentThread() + " Main thread");
        future.join();
    }

    /**
     * thenRun()/thenRunAsync() takes a Runnable
     * thenAccept()/thenAcceptAsync() takes a Consumer
     * thenApply()/thenApplyAsync() takes a Function
     */
    public void callbacks() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        completableFuture
                // as noted above, this callback is executed on current thread as the thenAcceptAsync() wasn't used
                .thenAccept(System.out::println);

        // manually complete the CF
        completableFuture.complete("I have completed!");
    }

    /**
     * thenCompose()/thenComposeAsync() starts when other completes
     * thenCombine()/thenCombineAsync() both run at same time, then combine
     * thenAcceptBoth()thenAcceptBothAsync()  both run at same time, then use not producing anything
     * acceptEither()/acceptEitherAsync() both run at same time, using first result
     */
    public void chainingCompletionStages() {

        CompletableFuture<Void> chain = new CompletableFuture<>();

        chain.thenComposeAsync(nil -> createUser())
                .thenAcceptAsync(logNewUserId())
                .thenComposeAsync(nil ->
                        registerAddress()
                                .thenAcceptBothAsync(registerPaymentDetails(), (address, paymentDetailsSuccess) -> {
                                    System.out.println(getCurrentThread() + "Registered address was : " + address);
                                    System.out.println(getCurrentThread() + "Registered payment details : " + paymentDetailsSuccess);
                                })
                )
                .thenComposeAsync(nil -> sendEmail())
                .thenAcceptAsync(result -> System.out.println(getCurrentThread() + "Email sent : " + result));

        chain.complete(null);
    }

    /**
     * Also whenComplete()/handle() exist for handling exceptions
     */
    public void exceptions() throws ExecutionException, InterruptedException {
        CompletableFuture<String> chain = CompletableFuture.runAsync(() -> System.out.println("Start"))
                .thenCombine(sendEmail(), (nil, text) -> {
                    if (!text.isEmpty())
                        throw new IllegalArgumentException("Text cannot be null or empty!");
                    return "COMPLETED";
                })
                .exceptionally(exception -> "FAILED");

        chain.join();
        System.out.println(chain.get());
    }

    public void timeouts() throws ExecutionException, InterruptedException {
        CompletableFuture<String> chain = CompletableFuture.runAsync(() -> System.out.println("Start"))
                .thenCombine(sendEmail(), (nil, text) -> {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "COMPLETED";
                })
                .completeOnTimeout("TIMEOUT", 200, TimeUnit.MILLISECONDS)
                .exceptionally(exception -> "FAILED");

        chain.join();
        System.out.println(chain.get());
    }
}