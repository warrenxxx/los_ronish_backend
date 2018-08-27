/**
 * The ResItinerarioDto class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :25/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.dto;

import io.unsaac.ronypsquienas.boletos_backend.models.Itinerario.CostosAsientosItinerario;
import io.unsaac.ronypsquienas.boletos_backend.models.Itinerario.Itinerario;
import io.unsaac.ronypsquienas.boletos_backend.models.Terminales;
import io.unsaac.ronypsquienas.boletos_backend.models.Vehiculo.Vehiculo;
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
public class ResItinerarioDto extends Itinerario {
    @Getter
    @Setter
    public Vehiculo vehiculo;
    @Getter
    @Setter
    public Terminales origen;
    @Getter
    @Setter
    public Terminales destino;

    public ResItinerarioDto(String id, Ref idVeiculo, Ref idterminalOrigen, Ref idterminalDestino, Date fechaSalida, Date fechaLLegada, CostosAsientosItinerario[] costosAsientosItinerarios, Vehiculo vehiculo, Terminales origen, Terminales destino) {
        super(id, idVeiculo, idterminalOrigen, idterminalDestino, fechaSalida, fechaLLegada, costosAsientosItinerarios);
        this.vehiculo = vehiculo;
        this.origen = origen;
        this.destino = destino;
    }

    public static ResItinerarioDto getItineraraio(Itinerario x){
        return new ResItinerarioDto(
                x.getId(),
                x.getIdVeiculo(),
                x.getIdterminalOrigen(),
                x.getIdterminalDestino(),
                x.getFechaSalida(),
                x.getFechaLLegada(),
                null,null,null,null
        );
    }
}
