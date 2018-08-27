/**
 * The ImageController class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :21/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.controller;

import io.unsaac.ronypsquienas.boletos_backend.Service.ReservaService;
import io.unsaac.ronypsquienas.boletos_backend.Service.VentaService;
import io.unsaac.ronypsquienas.boletos_backend.models.Reserva;
import io.unsaac.ronypsquienas.boletos_backend.models.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class VentasController {

    @Autowired
    private VentaService service;

    private final static String path="/ventas";
    private final Class aClass = Venta.class;

    @Bean
    public RouterFunction<?> ventasFunction() {
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
