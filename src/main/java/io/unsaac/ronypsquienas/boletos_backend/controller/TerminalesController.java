/**
 * The ImageController class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :21/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.controller;

import io.unsaac.ronypsquienas.boletos_backend.Service.ImageService;
import io.unsaac.ronypsquienas.boletos_backend.Service.TerminalesService;
import io.unsaac.ronypsquienas.boletos_backend.models.Identifier;
import io.unsaac.ronypsquienas.boletos_backend.models.Terminales;
import jdk.nashorn.internal.ir.Terminal;
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
public class TerminalesController {

    @Autowired
    private TerminalesService service;

    private final static String path="/terminales";
    private final Class aClass = Terminales.class;

    @Bean
    public RouterFunction<?> terminalFunction() {
        return  RouterFunctions.route(RequestPredicates.GET(path+"/{id}"),
                        request -> ServerResponse.ok()
                                .body(service.find(request.pathVariable("id")), aClass))
                .andRoute(RequestPredicates.GET(path),
                        request -> ServerResponse.ok()
                                .body(service.findAll(), aClass))
                .andRoute(RequestPredicates.POST(path),
                        request -> ServerResponse.ok()
                                .body(service.save(request.bodyToMono(aClass)), aClass))
                .andRoute(RequestPredicates.DELETE(path+"/{id}"),
                        request -> ServerResponse.ok()
                                .body(service.remove(request.pathVariable("id")), Boolean.class))
        ;
    }
}
