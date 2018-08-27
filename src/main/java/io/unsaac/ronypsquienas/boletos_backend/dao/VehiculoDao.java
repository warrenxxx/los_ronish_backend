/**
 * The TerminalesDao class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :23/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.dao;

import io.unsaac.ronypsquienas.boletos_backend.models.Vehiculo.Vehiculo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface VehiculoDao extends ReactiveMongoRepository<Vehiculo,String> {
}
