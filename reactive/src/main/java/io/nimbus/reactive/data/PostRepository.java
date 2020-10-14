package io.nimbus.reactive.data;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface PostRepository extends ReactiveCrudRepository<Post, Long> {

    @Query("SELECT * FROM posts WHERE title = :title")
    Flux<Post> findByTitle(String title);
}
