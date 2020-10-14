package io.nimbus.reactive.controller;

import io.nimbus.reactive.data.Post;
import io.nimbus.reactive.data.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    
    private final DatabaseClient client;
    private final PostRepository repository;
    
    @Override
    public Flux<Post> all() {

        //TODO alternatively using the DatabaseClient, useful for complex queries.
//        return client.execute("SELECT id, title, content FROM posts")
//                .as(Post.class)
//                .fetch()
//                .all();

        return repository.findAll();
    }

    @Override
    public Mono<Post> create(Post post) {
        return repository.save(post);
    }

    @Override
    public Mono<Post> get( Long id) {
        return repository.findById(id);
    }


    @Override
    public Mono<Post> update( Long id, @RequestBody Post post) {
        return repository.findById(id)
                .map(p -> {
                    p.setTitle(post.getTitle());
                    p.setContent(post.getContent());

                    return p;
                })
                .flatMap(repository::save);
    }

    @Override
    public Mono<Void> delete( Long id) {
        return repository.deleteById(id);
    }
}
