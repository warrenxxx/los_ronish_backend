/**
 * The UserDwao class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :20/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.dao;

import io.unsaac.ronypsquienas.boletos_backend.models.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserDao extends ReactiveMongoRepository<User,String> {
    Mono<User> findFirstByUser(String user);
}
