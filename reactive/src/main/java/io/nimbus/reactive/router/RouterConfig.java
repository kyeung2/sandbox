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
                .GET("person/{id}", accept(APPLICATION_JSON), handler::getPerson)
                .GET("person", accept(APPLICATION_JSON), handler::listPeople)
                .POST("person", handler::createPerson)
                .build();
    }

}
