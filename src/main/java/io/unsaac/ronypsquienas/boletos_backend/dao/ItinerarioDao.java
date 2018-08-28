/**
 * The UserDwao class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :20/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.dao;

import io.unsaac.ronypsquienas.boletos_backend.models.Itinerario.Itinerario;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ItinerarioDao extends ReactiveMongoRepository<Itinerario,String> {
    Flux<Itinerario> findAllByIdterminalOrigen_IdAndIdterminalDestino_Id(ObjectId origen, ObjectId destino);
}
