package io.flyingnimbus.usage;

import io.flyingnimbus.data.User;
import io.flyingnimbus.data.UserRepository;
import io.flyingnimbus.services.SomeService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OptimisticLockRepository {

    public static void use(SomeService someService, UserRepository userRepository) {

        System.out.println("Optimistic locking through Repository");
        System.out.println();
        userRepository.save(new User("Kye", "Yeung"));
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        try {
            //  This transaction is designed to fail as it takes too long to complete the transaction and the optimistically locked
            //  row has already been updated
            executorService.submit(() -> someService.repositoryUpdate(userRepository, "A", 3));
            TimeUnit.SECONDS.sleep(1);
            //  This transaction updates the same row, fast, successfully. Causing transaction1 to fail
            executorService.submit(() -> someService.repositoryUpdate(userRepository, "B", 0));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    private OptimisticLockRepository() {
    }
}
