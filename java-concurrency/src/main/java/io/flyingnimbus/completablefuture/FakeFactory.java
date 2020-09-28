package io.flyingnimbus.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;

public class FakeFactory {

    public static CompletionStage<String> sendEmail() {
        CompletableFuture<String> cf = new CompletableFuture<>();
        cf.complete("Email(to: yeung.kye@pm.me)");
        return cf;
    }

    public static CompletionStage<String> registerPaymentDetails() {
        CompletableFuture<String> cf = new CompletableFuture<>();
        cf.complete("Payment(£9.99)");
        return cf;
    }

    public static CompletionStage<String> registerAddress() {
        CompletableFuture<String> cf = new CompletableFuture<>();
        cf.complete("Address(78 Arcadian Gardens, N22 5AD)");
        return cf;
    }

    public static Consumer<String> logNewUserId() {
        return (String user) ->
            System.out.println(getCurrentThread() + "User created: " + user);
    }

    public static CompletionStage<String> createUser() {
        CompletableFuture<String> cf = new CompletableFuture<>();
        cf.complete("User(Kye Yeung)");
        return cf;
    }

    public static String getCurrentThread() {
        return "Thread[" + Thread.currentThread().getName() + "]\t";
    }
}