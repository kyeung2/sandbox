package io.flyingnimbus.services;

import io.flyingnimbus.data.Account;
import io.flyingnimbus.data.AccountRepository;
import io.flyingnimbus.data.User;
import io.flyingnimbus.data.UserRepository;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Service
public class SomeService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public SomeService(AccountRepository accountRepository, UserRepository userRepository) {

        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    // generally this is the mechanism to make a compound operation, involving more than one table transactional
    @Transactional
    public void doSomethingWithTwoTables() {

        accountRepository.save(new Account("kye1", "password1", "1my@email.com", LocalDateTime.now()));
        userRepository.save(new User("Kye", "Yeung"));
    }


    // this is proving out the optimistic locking mechanism wih @Lock on the UserRepository
    @Transactional
    public void repositoryUpdate(UserRepository userRepository, String newLastName, long seconds) {

        String currentThread = Thread.currentThread().getName();
        try {

            // optimistic lock is obtained at this point
            User user = userRepository.findByFirstName("Kye");
            System.out.println(currentThread + ", user,  " + user);

            user.setLastName(newLastName);
            System.out.println(currentThread + ", about to update after SECONDS: " + seconds);
            TimeUnit.SECONDS.sleep(seconds);
            userRepository.save(user);
            User updatedUser = userRepository.findByFirstName("Kye");
            System.out.println(currentThread + ", updated user, " + updatedUser);

        } catch (ObjectOptimisticLockingFailureException e) {
            System.out.println(currentThread + ", great the exception expected: " + e.getMessage());

        } catch (Exception e) {
            System.out.println(currentThread + ", something else happened...");
            e.printStackTrace();
        }
    }


}
