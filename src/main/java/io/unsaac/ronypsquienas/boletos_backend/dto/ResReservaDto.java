/**
 * The ResReservaItinerarioDto class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :25/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.dto;

import io.unsaac.ronypsquienas.boletos_backend.models.Person;
import io.unsaac.ronypsquienas.boletos_backend.models.Reserva;
import io.unsaac.ronypsquienas.boletos_backend.utils.Ref;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Document
@Accessors(chain = true)
public class ResReservaDto extends Reserva {
    @Getter
    @Setter
    private Person pasajero;
    @Getter
    @Setter
    private ResItinerarioDto itinerario;

    public ResReservaDto(String id, Ref idPersonaPasajero, Date fechaReserva, Ref idItinerario, Boolean stado, Integer numeroAsiento, Person pasajero, ResItinerarioDto itinerario) {
        super(id, idPersonaPasajero, fechaReserva, idItinerario, stado, numeroAsiento);
        this.pasajero = pasajero;
        this.itinerario = itinerario;
    }
}
