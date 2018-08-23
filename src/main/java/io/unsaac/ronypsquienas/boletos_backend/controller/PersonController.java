/**
 * The ImageController class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :21/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.controller;

import io.unsaac.ronypsquienas.boletos_backend.Service.ImageService;
import io.unsaac.ronypsquienas.boletos_backend.Service.PersonService;
import io.unsaac.ronypsquienas.boletos_backend.models.Identifier;
import io.unsaac.ronypsquienas.boletos_backend.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.Part;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class PersonController {


    @Autowired
    private PersonService service;

    private String path="/person";

    @Bean
    public RouterFunction<?> personRouterFunction() {
        return RouterFunctions.nest(RequestPredicates.path("/api"),
                RouterFunctions
                        .route(RequestPredicates.GET(path),
                                request -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(service.findAll(), Person.class))
                        .andRoute(RequestPredicates.POST(path),
                                request -> ServerResponse.ok()
                                        .body(service.save(request.bodyToMono(Person.class)), Person.class))
                        .andRoute(RequestPredicates.GET(path+"/{id}"),
                                request -> ServerResponse.ok()
                                        .body(service.findById(request.pathVariable("id")), Person.class))
                        .andRoute(RequestPredicates.DELETE(path+"/{id}"),
                                request -> ServerResponse.ok()
                                        .body(service.remove(request.pathVariable("id")), Boolean.class))
        );
    }
}
