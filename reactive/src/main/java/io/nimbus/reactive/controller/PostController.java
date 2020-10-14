package io.nimbus.reactive.controller;


import io.nimbus.reactive.data.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController()
@RequestMapping(value = "/controller/posts")
@RequiredArgsConstructor
class PostController {

    private final PostService postService;

    @GetMapping("")
    public Flux<Post> all() {
        return postService.all();
    }

    @PostMapping("")
    @ResponseStatus(CREATED)
    public Mono<Post> create(@RequestBody Post post) {
        return postService.create(post);
    }

    @GetMapping("/{id}")
    public Mono<Post> get(@PathVariable("id") Long id) {
        return postService.get(id);
    }

    @PutMapping("/{id}")
    public Mono<Post> update(@PathVariable("id") Long id, @RequestBody Post post) {
        return postService.update(id, post);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public Mono<Void> delete(@PathVariable("id") Long id) {
        return postService.delete(id);
    }

}