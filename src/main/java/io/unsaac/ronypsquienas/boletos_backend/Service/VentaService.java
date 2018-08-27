/**
 * The UserService class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :20/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.Service;

import io.unsaac.ronypsquienas.boletos_backend.dao.VentaDao;
import io.unsaac.ronypsquienas.boletos_backend.models.Venta;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VentaService {
    @Autowired
    VentaDao dao;

    public Mono<Venta> save(Mono<Venta> terminalesMono){
        return terminalesMono
                .map(e->e.getId().equals("") ?e.setId(new ObjectId().toString()):e)
                .flatMap(e->dao.save(e));
    }
    public Mono<Boolean> remove(String id){
        return dao.deleteById(id).hasElement();
    }
    public Mono<Venta> find(String id){
        return dao.findById(id);
    }
    public Flux<Venta> findAll(){
        return dao.findAll();
    }
}
