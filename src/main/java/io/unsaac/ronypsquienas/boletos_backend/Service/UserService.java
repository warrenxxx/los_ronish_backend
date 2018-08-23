/**
 * The UserService class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :20/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.Service;

import io.unsaac.ronypsquienas.boletos_backend.dao.PersonDao;
import io.unsaac.ronypsquienas.boletos_backend.dao.UserDao;
import io.unsaac.ronypsquienas.boletos_backend.dto.LoginDto;
import io.unsaac.ronypsquienas.boletos_backend.dto.ReqRegisterDto;
import io.unsaac.ronypsquienas.boletos_backend.dto.ResLoginDto;
import io.unsaac.ronypsquienas.boletos_backend.models.Person;
import io.unsaac.ronypsquienas.boletos_backend.models.User;
import io.unsaac.ronypsquienas.boletos_backend.utils.Ref;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Service
public class UserService {
    @Autowired
    UserDao dao;

    @Autowired
    PersonDao personDao;
    public Mono<ServerResponse> insert(ServerRequest request){
        return request.bodyToMono(ReqRegisterDto.class)
                .map(e->e
                        .setPassword(BCrypt.hashpw(e.getPassword(), BCrypt.gensalt()))
                )
                .flatMap(e->personDao.insert(e.getPerson())
                        .flatMap(f->dao.insert(e.getUser().setIdPerson(Ref.PersonRef(f.getId()))))
                )

                .flatMap(e->ok().build());
    }
    public Mono<ServerResponse> update(ServerRequest request){
        return request.bodyToMono(User.class)
                .flatMap(dao::save)
                .flatMap(e->ok().build());
    }
    public Mono<ServerResponse> remove(ServerRequest request){
        return request.bodyToMono(User.class)
                .flatMap(dao::delete)
                .hasElement()
                .flatMap(e->ok().build());
    }
    public Mono<ServerResponse> find(ServerRequest request){
        return ok()
                .body(
                        dao.findById(request.pathVariable("id")),
                        User.class
                );
    }
    public Mono<ServerResponse> findAll(ServerRequest request){
        return ok().body(dao.findAll(), User.class);
    }

    public Mono<ServerResponse> register(ServerRequest request){
        return this.insert(request);
    }
    public Mono<ServerResponse> login(ServerRequest request){

        return request.bodyToMono(LoginDto.class)
                .flatMap(e->
                        dao.findFirstByUser(e.getUser())
                                .filter(user->BCrypt.checkpw(e.getPassword(), user.getPassword()))
                                .switchIfEmpty(Mono.error(new Exception("no existe")))
                )
                .flatMap(e->personDao.findById(e.getIdPerson().id)
                    .map(f->new ResLoginDto(e.getId(),e.getUser(),e.getPassword(),e.getRole(),e.getIdPerson(),f))
                ).doOnNext(System.out::println).
                        flatMap(e->ok().body(Mono.just(e),ResLoginDto.class));
    }

    @Autowired
    GridFsOperations operations;


}
