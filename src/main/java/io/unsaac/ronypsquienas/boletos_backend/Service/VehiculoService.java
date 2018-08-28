/**
 * The UserService class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :20/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.Service;

import io.unsaac.ronypsquienas.boletos_backend.dao.VehiculoDao;
import io.unsaac.ronypsquienas.boletos_backend.dto.ResVehiculoDto;
import io.unsaac.ronypsquienas.boletos_backend.models.Person;
import io.unsaac.ronypsquienas.boletos_backend.models.Vehiculo.Asiento;
import io.unsaac.ronypsquienas.boletos_backend.models.Vehiculo.Vehiculo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VehiculoService {
    @Autowired
    VehiculoDao dao;

    @Autowired
    PersonService personService;

    public Mono<Vehiculo> save(Mono<Vehiculo> terminalesMono){
        return terminalesMono
                .map(e->e.getId().equals("") ?e.setId(new ObjectId().toString()):e)
                .flatMap(e->dao.save(e));
    }
    public Mono<Boolean> remove(String id){
        return dao.deleteById(id).hasElement();
    }
    public Mono<Vehiculo> find(String id){
        return dao.findById(id);
    }
    public Flux<ResVehiculoDto> findAll(){
        return dao.findAll()
                .map(e->new ResVehiculoDto(
                    e.getId(),
                    e.getModelo(),
                    e.getNroPisos(),
                    e.getTipoVeiculo(),
                    e.getIdPersonaTerramozas(),
                    e.getIdPersonaChoferes(),
                    e.getAsientos(),e.getImg(),null,null,null
                )).flatMap(e->
                    Flux.just(e.getIdPersonaChoferes())
                            .flatMap(idChoferes->personService.findById(idChoferes.id))
                            .collectList()
                            .map(choferes->e.setChoferes(choferes.toArray(new Person[0])))
                ).flatMap(e->
                        Flux.just(e.getIdPersonaTerramozas())
                                .flatMap(idTeramozas->personService.findById(idTeramozas.id))
                                .collectList()
                                .map(terramozas->e.setTerramozas(terramozas.toArray(new Person[0])))
                ).flatMap(e->
                        Flux.just(e.getAsientos())
                                .map(Asiento::getType)
                                .collectList()
                                .map(f->e.setTypos(f.toArray(new String[0])))
                        )
                ;
    }
}
