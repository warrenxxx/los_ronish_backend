/**
 * The UserService class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :20/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.Service;

import io.unsaac.ronypsquienas.boletos_backend.dao.*;
import io.unsaac.ronypsquienas.boletos_backend.dto.ResItinerarioDto;
import io.unsaac.ronypsquienas.boletos_backend.dto.ResReservaDto;
import io.unsaac.ronypsquienas.boletos_backend.models.Reserva;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReservaService {
    @Autowired
    ReservaDao dao;

    @Autowired
    PersonDao personDao;
    @Autowired
    ItinerarioDao itinerarioDao;
    @Autowired
    TerminalesDao terminalesDao;
    @Autowired
    VehiculoDao vehiculoDao;

    public Mono<Reserva> save(Mono<Reserva> terminalesMono){
        return terminalesMono
                .map(e->e.getId().equals("") ?e.setId(new ObjectId().toString()):e)
                .flatMap(e->dao.save(e));
    }
    public Mono<Boolean> remove(String id){
        return dao.deleteById(id).hasElement();
    }
    public Mono<Reserva> find(String id){
        return dao.findById(id);
    }
    public Flux<ResReservaDto> findAll(){
        return dao.findAll().map(e->new ResReservaDto(
                e.getId(),
                e.getIdPersonaPasajero(),
                e.getFechaReserva(),
                e.getIdItinerario(),
                e.getStado(),
                e.getNumeroAsiento(),null,null
        )).flatMap(
                e->personDao.findById(e.getIdPersonaPasajero().id).map(e::setPasajero)
        ).flatMap(
                e->itinerarioDao.findById(e.getIdItinerario().id).map(ResItinerarioDto::getItineraraio)
                        .flatMap(resItinerarioDto->
                                terminalesDao.findById(resItinerarioDto.getIdterminalDestino().id)
                                .map(resItinerarioDto::setDestino)
                        ).flatMap(resItinerarioDto->
                                terminalesDao.findById(resItinerarioDto.getIdterminalOrigen().id)
                                        .map(resItinerarioDto::setOrigen)
                        ).flatMap(resItinerarioDto->
                                vehiculoDao.findById(resItinerarioDto.getIdVeiculo().id)
                                        .map(resItinerarioDto::setVehiculo)
                        ).map(e::setItinerario)
        );
    }
}
