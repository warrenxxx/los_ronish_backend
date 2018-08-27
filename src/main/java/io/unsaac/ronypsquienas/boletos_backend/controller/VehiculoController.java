/**
 * The ImageController class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :21/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.controller;

import io.unsaac.ronypsquienas.boletos_backend.Service.VehiculoService;
import io.unsaac.ronypsquienas.boletos_backend.models.Vehiculo.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class VehiculoController {

    @Autowired
    private VehiculoService service;

    private final static String path="/vehiculo";
    private final Class aClass = Vehiculo.class;

    @Bean
    public RouterFunction<?> vehiculoFunction() {
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
