package io.nimbus.reactive.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    RouterFunction<ServerResponse> router(PostHandler handler) {
        return route()
                .GET("router/posts/{id}", accept(APPLICATION_JSON), handler::get)
                .GET("router/posts", accept(APPLICATION_JSON), handler::all)
                .POST("router/posts", accept(APPLICATION_JSON), handler::create)
                .PUT("router/posts/{id}", accept(APPLICATION_JSON), handler::update)
                .DELETE("router/posts/{id}", accept(APPLICATION_JSON), handler::delete)
                .build();
    }

}
