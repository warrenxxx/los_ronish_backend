/**
 * The UserService class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :20/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.Service;

import io.unsaac.ronypsquienas.boletos_backend.dao.ItinerarioDao;
import io.unsaac.ronypsquienas.boletos_backend.dao.ReservaDao;
import io.unsaac.ronypsquienas.boletos_backend.dao.TerminalesDao;
import io.unsaac.ronypsquienas.boletos_backend.dao.VehiculoDao;
import io.unsaac.ronypsquienas.boletos_backend.dto.ReqItinerarioBuscar;
import io.unsaac.ronypsquienas.boletos_backend.dto.ResItinerarioDto;
import io.unsaac.ronypsquienas.boletos_backend.models.Itinerario.Itinerario;
import io.unsaac.ronypsquienas.boletos_backend.models.Reserva;
import org.bson.types.ObjectId;
import org.joda.time.DateTimeComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Calendar;

@Service
public class ItinerarioService {
    @Autowired
    ItinerarioDao dao;

    @Autowired
    TerminalesDao terminalesDao;

    @Autowired
    VehiculoDao vehiculoDao;

    @Autowired
    ReservaDao reservaDao;

    public Mono<Itinerario> save(Mono<Itinerario> terminalesMono){
        return terminalesMono
                .map(e->e.getId().equals("") ?e.setId(new ObjectId().toString()):e)
                .flatMap(e->dao.save(e));
    }
    public Mono<Boolean> remove(String id){
        return dao.deleteById(id).hasElement();
    }
    public Mono<Itinerario> find(String id){
        return dao.findById(id);
    }
    public Flux<ResItinerarioDto> findAll(){
        dao.findAll().count().subscribe(System.out::println);
        dao.findAll().map(e->new ResItinerarioDto(
                e.getId(),
                e.getIdVeiculo(),
                e.getIdterminalOrigen(),
                e.getIdterminalDestino(),
                e.getFechaSalida(),
                e.getFechaLLegada(),
                e.getCosto(),null,null,null,new Integer[]{}
        )).flatMap(
                e->vehiculoDao.findById(e.getIdVeiculo().id)
                        .map(e::setVehiculo)
        ).flatMap(
                e->terminalesDao.findById(e.getIdterminalOrigen().id)
                        .map(e::setOrigen)
        ).flatMap(
                e->terminalesDao.findById(e.getIdterminalDestino().id)
                        .map(e::setDestino)
        ).count().subscribe(System.out::println);
        return dao.findAll().map(e->new ResItinerarioDto(
                e.getId(),
                e.getIdVeiculo(),
                e.getIdterminalOrigen(),
                e.getIdterminalDestino(),
                e.getFechaSalida(),
                e.getFechaLLegada(),
                e.getCosto(),null,null,null,null
        )).flatMap(
                e->vehiculoDao.findById(e.getIdVeiculo().id)
                        .map(e::setVehiculo)
        ).flatMap(
                e->terminalesDao.findById(e.getIdterminalOrigen().id)
                        .map(e::setOrigen)
        ).flatMap(
                e->terminalesDao.findById(e.getIdterminalDestino().id)
                        .map(e::setDestino)
        );
    }

    public Flux<ResItinerarioDto> findDetail(Mono<ReqItinerarioBuscar> bodyToMono) {
        return bodyToMono.flux().flatMap(
                buscar ->
                        dao.findAllByIdterminalOrigen_IdAndIdterminalDestino_Id(
                                new ObjectId(buscar.getOrigen()),
                                new ObjectId(buscar.getDestino())
                        ).filter(itinerario -> {
                            DateTimeComparator dateTimeComparator = DateTimeComparator.getDateOnlyInstance();
                            int r1 = dateTimeComparator.compare(buscar.getFecha(), itinerario.getFechaSalida());
                            if(r1==0){
                                    System.out.println(buscar.getFecha()+" "+itinerario.getFechaSalida() );
                            }
                            return r1==0;
                        })
        ).map(e->new ResItinerarioDto(
                e.getId(),
                e.getIdVeiculo(),
                e.getIdterminalOrigen(),
                e.getIdterminalDestino(),
                e.getFechaSalida(),
                e.getFechaLLegada(),
                e.getCosto(),null,null,null,null
        )).flatMap(
                e->vehiculoDao.findById(e.getIdVeiculo().id)
                        .map(e::setVehiculo)
        ).flatMap(
                e->terminalesDao.findById(e.getIdterminalOrigen().id)
                        .map(e::setOrigen)
        ).flatMap(
                e->terminalesDao.findById(e.getIdterminalDestino().id)
                        .map(e::setDestino)
        ).flatMap(
                e->reservaDao.findAllByIdItinerario_Id(new ObjectId(e.getId()))
                        .map(Reserva::getNumeroAsiento)
                        .collectList()
                        .map(f->e.setReservados(f.toArray(new Integer[0])))
        );
    }
}

  
