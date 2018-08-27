/**
 * The UserService class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :20/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.Service;

import io.unsaac.ronypsquienas.boletos_backend.dao.ItinerarioDao;
import io.unsaac.ronypsquienas.boletos_backend.dao.TerminalesDao;
import io.unsaac.ronypsquienas.boletos_backend.dao.VehiculoDao;
import io.unsaac.ronypsquienas.boletos_backend.dto.ResItinerarioDto;
import io.unsaac.ronypsquienas.boletos_backend.models.Itinerario.Itinerario;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ItinerarioService {
    @Autowired
    ItinerarioDao dao;

    @Autowired
    TerminalesDao terminalesDao;

    @Autowired
    VehiculoDao vehiculoDao;

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
                e.getCostosAsientosItinerarios(),null,null,null
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
                e.getCostosAsientosItinerarios(),null,null,null
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
}
