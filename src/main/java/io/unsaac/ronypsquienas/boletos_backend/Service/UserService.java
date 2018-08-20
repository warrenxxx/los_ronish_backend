/**
 * The UserService class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :20/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.Service;

import io.unsaac.ronypsquienas.boletos_backend.dao.UserDao;
import io.unsaac.ronypsquienas.boletos_backend.dto.LoginDto;
import io.unsaac.ronypsquienas.boletos_backend.models.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Service
public class UserService {
    @Autowired
    UserDao dao;
    public Mono<ServerResponse> insert(ServerRequest request){
        return request.bodyToMono(User.class)
                .map(e->e.setPassword(BCrypt.hashpw(e.getPassword(), BCrypt.gensalt())))
                .flatMap(dao::insert)
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
                                .doOnNext(System.out::println)
                                .filter(user->BCrypt.checkpw(e.getPassword(), user.getPassword()))
                        .defaultIfEmpty(new User().setUser("no existe"))
                ).flatMap(e->ok().body(Mono.just(e),User.class));
    }

}
