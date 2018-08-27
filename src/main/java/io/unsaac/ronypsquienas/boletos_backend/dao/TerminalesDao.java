/**
 * The TerminalesDao class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :23/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.dao;

import io.unsaac.ronypsquienas.boletos_backend.models.Terminales;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TerminalesDao extends ReactiveMongoRepository<Terminales,String> {
}
