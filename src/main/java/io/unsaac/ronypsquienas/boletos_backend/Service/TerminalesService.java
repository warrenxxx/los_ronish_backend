/**
 * The UserService class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :20/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.Service;

import io.unsaac.ronypsquienas.boletos_backend.dao.PersonDao;
import io.unsaac.ronypsquienas.boletos_backend.dao.TerminalesDao;
import io.unsaac.ronypsquienas.boletos_backend.dao.UserDao;
import io.unsaac.ronypsquienas.boletos_backend.dto.LoginDto;
import io.unsaac.ronypsquienas.boletos_backend.dto.ReqRegisterDto;
import io.unsaac.ronypsquienas.boletos_backend.dto.ResLoginDto;
import io.unsaac.ronypsquienas.boletos_backend.models.Terminales;
import io.unsaac.ronypsquienas.boletos_backend.models.User;
import io.unsaac.ronypsquienas.boletos_backend.utils.Ref;
import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Service
public class TerminalesService {
    @Autowired
    TerminalesDao dao;

    public Mono<Terminales> save(Mono<Terminales> terminalesMono){
        return terminalesMono
                .map(e->e.getId().equals("") ?e.setId(new ObjectId().toString()):e)
                .flatMap(e->dao.save(e));
    }
    public Mono<Boolean> remove(String id){
        return dao.deleteById(id).hasElement();
    }
    public Mono<Terminales> find(String id){
        return dao.findById(id);
    }
    public Flux<Terminales> findAll(){
        return dao.findAll();
    }
}
