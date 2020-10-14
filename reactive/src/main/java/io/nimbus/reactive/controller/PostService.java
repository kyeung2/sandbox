package io.nimbus.reactive.controller;

import io.nimbus.reactive.data.Post;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostService {


    Flux<Post> all();

    Mono<Post> create(Post post);

    Mono<Post> get(Long id);

    Mono<Post> update(Long id, Post post);

    Mono<Void> delete(Long id);

}
