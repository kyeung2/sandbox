package io.flyingnimbus.usage;

import io.flyingnimbus.data.User;
import io.flyingnimbus.data.UserRepository;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.RollbackException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OptimisticLockEntityManager {

    public static void use(UserRepository userRepository, EntityManager em) {

        System.out.println("Optimistic locking through EntityManager");
        System.out.println();

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        userRepository.save(new User("Kye", "Yeung"));

        executorService.submit(getTransaction1(em));
        executorService.submit(getTransaction2(em));

        executorService.shutdown();
    }

    /**
     * This transaction is designed to fail as it takes too long to complete the transaction and the optimistically locked
     * row has already been updated
     */
    private static Runnable getTransaction1(EntityManager em) {
        return () -> {
            String currentThread = Thread.currentThread().getName();
            try {

                System.out.println(currentThread + ": Start a transaction with an Optimistic lock");
                em.getTransaction().begin();
                User user = em.find(User.class, 1L,
                        LockModeType.OPTIMISTIC_FORCE_INCREMENT// adding the lock requires the EntityManager to have a transaction in progress, obviously
                );
                System.out.println(currentThread + ": " + user);

                System.out.println(currentThread + ": takes some tie (2 seconds)");
                TimeUnit.SECONDS.sleep(2);
                user.setLastName("UPDATED");
                em.getTransaction().commit();
                System.out.println(currentThread + ": Shouldn't go to this line");

            } catch (RollbackException e) {
                System.out.println(currentThread + ": Great the result we wanted, " +
                        e.getCause().getLocalizedMessage());

                // Optimistic lock worked now code can take action, inform user, retry etc etc
            } catch (Exception e) {
                System.out.println(currentThread + ": something else went wrong");
                e.printStackTrace();
            }
        };
    }

    /**
     * This transaction updates the same row, fast, successfully. Causing transaction1 to fail
     */
    private static Runnable getTransaction2(EntityManager em) {
        return () -> {
            String currentThread = Thread.currentThread().getName();
            System.out.println(currentThread + ": Another thread updates the same row on the same version");
            User user = em.find(User.class, 1L);
            System.out.println(currentThread + ": " + user);
            user.setLastName("Me first!");
            // Bit of a hack, but serves the purpose of getting another thread of execution to update the row, while the other started a transaction on an optimistic lock
            Session session = em.unwrap(Session.class);
            session.update(user);
            System.out.println(currentThread + ": Committed");
            System.out.println();
        };
    }

    private OptimisticLockEntityManager() {
    }
}
