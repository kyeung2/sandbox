package io.nimbus.reactive.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.ContextConfiguration;
import reactor.test.StepVerifier;

@DataR2dbcTest
@ContextConfiguration(classes = {DatabaseConfig.class})
class PostRepositoryTest {

    @Autowired
    private PostRepository objectUnderTest;

    @BeforeEach
    void setup() {
        objectUnderTest.deleteAll().block();
        objectUnderTest.save(Post.builder().title("post 1").content("content 1").build()).block();
        objectUnderTest.save(Post.builder().title("post 2").content("content 2").build()).block();
        objectUnderTest.save(Post.builder().title("post 3").content("content 3").build()).block();
    }

    @Test
    void findAll() {
        objectUnderTest
                .findAll()
                .as(StepVerifier::create)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void findByTitle() {
        objectUnderTest
                .findByTitle("post 2")
                .as(StepVerifier::create)
                .expectNextMatches(p -> p.getTitle().equals("post 2"))
                .verifyComplete();
    }

}