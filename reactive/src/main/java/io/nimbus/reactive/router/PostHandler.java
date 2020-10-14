package io.nimbus.reactive.router;

import io.nimbus.reactive.data.Post;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Service
public class PostHandler {

    public Mono<ServerResponse> listPeople(ServerRequest request) {
        Post p = Post.builder().id(1).title("some title").content("some content").build();
        Post p2 = Post.builder().id(2).title("some title").content("some content").build();

        Flux<Post> people = Flux.just(p,p2);
        return ok().contentType(APPLICATION_JSON).body(people, Post.class);
    }


    public Mono<ServerResponse> createPerson(ServerRequest request) {
        return stubbed();
    }

    public Mono<ServerResponse> getPerson(ServerRequest request) {
        return stubbed();

    }


    private Mono<ServerResponse> stubbed() {
        Post p = Post.builder().id(1).title("some title").content("some content").build();
        return ok()
                .contentType(APPLICATION_JSON)
                .bodyValue(p);
    }
}


