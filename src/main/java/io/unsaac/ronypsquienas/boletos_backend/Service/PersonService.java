/**
 * The PersonService class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :22/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.Service;

import io.unsaac.ronypsquienas.boletos_backend.dao.PersonDao;
import io.unsaac.ronypsquienas.boletos_backend.models.Person;
import io.unsaac.ronypsquienas.boletos_backend.utils.ComboBoxResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonService {

    @Autowired
    PersonDao dao;
    public Flux<Person> findAll() {
        return dao.findAll();
    }

    public Mono<Person> save(Mono<Person> bodyToMono) {
        return bodyToMono
                .map(e->e.getId().equals("") ?e.setId(new ObjectId().toString()):e)
                .flatMap(dao::save);
    }

    public Mono<Person> findById(String id) {
        return dao.findById(id);
    }

    public Mono<Boolean> remove(String id) {
        return dao.deleteById(id).hasElement();
    }

    public Flux<ComboBoxResponse> getBox() {
        return dao.findAll().map(e->new ComboBoxResponse(e.getId(),e.getDni()+" "+e.getNombre()));
    }
}
