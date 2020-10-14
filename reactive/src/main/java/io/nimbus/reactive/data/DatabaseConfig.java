package io.nimbus.reactive.data;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.data.r2dbc.connectionfactory.init.CompositeDatabasePopulator;
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer;
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.Duration;
import java.util.List;


@Configuration
@EnableR2dbcRepositories
@EnableTransactionManagement
@Slf4j
public class DatabaseConfig extends AbstractR2dbcConfiguration {

    @Override
    @Bean
    public ConnectionFactory connectionFactory() {

        return new H2ConnectionFactory(H2ConnectionConfiguration.builder()
                .inMemory("testdb")
                .username("sa")
                .option("DB_CLOSE_DELAY=-1")
                .build());
    }

    @Bean
    ReactiveTransactionManager transactionManager(@Qualifier("connectionFactory") ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }


    @Bean
     ConnectionFactoryInitializer initializer(@Qualifier("connectionFactory") ConnectionFactory connectionFactory) {

        var initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);

        var p = new CompositeDatabasePopulator();
        p.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
        p.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("data.sql")));
        initializer.setDatabasePopulator(p);

        return initializer;
    }

    @Bean
    public CommandLineRunner demoData(PostRepository repository) {

        return (args) -> {
            // save a few posts
            repository.saveAll(List.of(
                    Post.builder().title("post 1").content("content 1").build(),
                    Post.builder().title("post 2").content("content 2").build(),
                    Post.builder().title("post 3").content("content 3").build(),
                    Post.builder().title("post 4").content("content 4").build(),
                    Post.builder().title("post 5").content("content 5").build(),
                    Post.builder().title("post 6").content("content 6").build()))
                    .blockLast(Duration.ofSeconds(10));

            // fetch all posts
            log.info("Post found with findAll():");
            log.info("-------------------------------");
            repository.findAll().doOnNext(p -> log.info(p.toString())
            ).blockLast(Duration.ofSeconds(10));

            log.info("");

            // fetch an individual post by ID
            repository.findById(1L).doOnNext(customer -> {
                log.info("Post found with findById(1L):");
                log.info("--------------------------------");
                log.info(customer.toString());
                log.info("");
            }).block(Duration.ofSeconds(10));


            // fetch post by title
            log.info("Post found with findByTitle('post 5'):");
            log.info("--------------------------------------------");
            repository.findByTitle("post 5").doOnNext(p -> log.info(p.toString())
            ).blockLast(Duration.ofSeconds(10));
            log.info("");
        };
    }

}
