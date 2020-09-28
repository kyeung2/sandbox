package io.flyingnimbus;

import io.flyingnimbus.data.AccountRepository;
import io.flyingnimbus.data.UserRepository;
import io.flyingnimbus.services.SomeService;
import io.flyingnimbus.usage.OptimisticLockRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManagerFactory;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(AccountRepository repository, UserRepository userRepository, SomeService someService, EntityManagerFactory emf) {
        return (args) -> {

            //NOTE: uncomment each scenario to understand various aspects of JPA usage

            //Scenario1: basic use of Spring Data JpaRepository
            //Basics.use(repository);

            //Scenario2: calling a @Transactional method, needs to be called from a different class to the one that contains the @Transactional method.
            //someService.doSomethingWithTwoTables();

            //Scenario3: example of using an optimistic lock with an EntityManager and it throwing an exception when row already updated after transaction started
            //OptimisticLockEntityManager.use(userRepository, emf.createEntityManager());

            //Scenario4: example of using an optimistic lock with a Spring Data JpaRepository and throwing an exception when another transaction write to the locked row, i.e. version is bumped
            OptimisticLockRepository.use(someService, userRepository);

        };
    }

}
