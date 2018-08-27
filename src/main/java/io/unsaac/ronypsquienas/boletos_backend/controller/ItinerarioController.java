/**
 * The ImageController class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :21/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.controller;

import io.unsaac.ronypsquienas.boletos_backend.Service.ItinerarioService;
import io.unsaac.ronypsquienas.boletos_backend.dao.ReservaDao;
import io.unsaac.ronypsquienas.boletos_backend.dto.ResItinerarioDto;
import io.unsaac.ronypsquienas.boletos_backend.models.Itinerario.Itinerario;
import io.unsaac.ronypsquienas.boletos_backend.models.Reserva;
import io.unsaac.ronypsquienas.boletos_backend.utils.Ref;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Date;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class ItinerarioController {

    @Autowired
    private ItinerarioService service;

    private final static String path="/itinerario";
    private final Class aClass = Itinerario.class;

    @Bean
    public CommandLineRunner commandLineRunner(ReservaDao dao){
        return args -> {

//                    dao.save(new Reserva(
//                            new  ObjectId().toString(),
//                            Ref.PersonRef("5b80b6e2acfdb621a4b64f56"),
//                            new Date(),
//                            Ref.ItinerarioRef("5b81faefee69ac27004c0432"),
//                            true,
//                            0
//                    )).subscribe();


        };
    }

    @Bean
    public RouterFunction<ServerResponse> itinerarioFunction() {
        return  RouterFunctions
                .route(RequestPredicates.GET(path+"/{id}"),
                        request -> ok().body(service.find(request.pathVariable("id")), aClass))
                .andRoute(RequestPredicates.GET(path),
                        request -> ok().body(service.findAll(), ResItinerarioDto.class))
                .andRoute(RequestPredicates.POST(path),
                        request -> ok().body(service.save(request.bodyToMono(aClass)), aClass))
                .andRoute(RequestPredicates.DELETE(path+"/{id}"),
                        request -> ok().body(service.remove(request.pathVariable("id")), Boolean.class))
                ;
    }
}
