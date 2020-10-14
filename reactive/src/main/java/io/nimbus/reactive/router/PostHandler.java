package io.nimbus.reactive.router;

import io.nimbus.reactive.controller.PostService;
import io.nimbus.reactive.data.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.created;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Service
@AllArgsConstructor
public class PostHandler {

    private final PostService postService;

    public Mono<ServerResponse> all(ServerRequest request) {
        return ok().contentType(APPLICATION_JSON).body(postService.all(), Post.class);
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(Post.class)
                .flatMap(postService::create)
                .flatMap(p ->
                        created(URI.create("/router/posts/" + p.getId()))
                                .contentType(APPLICATION_JSON)
                                .build());
    }

    public Mono<ServerResponse> get(ServerRequest request) {
        long id = Long.parseLong(request.pathVariable("id"));
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(postService.get(id), Post.class);
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        long id = Long.parseLong(request.pathVariable("id"));
        return request.bodyToMono(Post.class)
                .flatMap(p -> postService.update(id, p))
                .flatMap(p -> ServerResponse.ok().contentType(APPLICATION_JSON)
                        .bodyValue(p));
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        long id = Long.parseLong(request.pathVariable("id"));
        return postService.delete(id).flatMap(v -> ServerResponse.noContent().build());
    }

    private Mono<ServerResponse> stubbed() {
        Post p = Post.builder().id(1).title("some title").content("some content").build();
        return ok()
                .contentType(APPLICATION_JSON)
                .bodyValue(p);
    }
}


