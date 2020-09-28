package io.flyingnimbus.usage;

import io.flyingnimbus.data.Account;
import io.flyingnimbus.data.AccountRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public class Basics {

    public static void use(AccountRepository repository) {
        System.out.println("Basic use of a Spring JpaRepository");

        repository.save(new Account("kye1", "password1", "1my@email.com", LocalDateTime.now()));
        repository.save(new Account("kye2", "password2", "2my@email.com", LocalDateTime.now()));

        System.out.println("Accounts found with findAll():");
        System.out.println("-------------------------------");
        repository.findAll().forEach(System.out::println);
        System.out.println();

        Optional<Account> account = repository.findById(1L);
        System.out.println("Account found with findById(1L):");
        System.out.println("--------------------------------");
        System.out.println(account);
        System.out.println();

        System.out.println("Account found with findByEmail('2my@email.com'):");
        System.out.println("--------------------------------------------");
        repository.findByEmail("2my@email.com").forEach(System.out::println);
        System.out.println();
    }

    private Basics(){}
}
