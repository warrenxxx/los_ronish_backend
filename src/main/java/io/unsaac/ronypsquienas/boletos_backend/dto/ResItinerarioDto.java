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

    @Getter
    @Setter
    public Integer [] reservados;

    public ResItinerarioDto(String id, Ref idVeiculo, Ref idterminalOrigen, Ref idterminalDestino, Date fechaSalida, Date fechaLLegada, Double costo, Vehiculo vehiculo, Terminales origen, Terminales destino, Integer[] reservados) {
        super(id, idVeiculo, idterminalOrigen, idterminalDestino, fechaSalida, fechaLLegada, costo);
        this.vehiculo = vehiculo;
        this.origen = origen;
        this.destino = destino;
        this.reservados = reservados;
    }

    public static ResItinerarioDto getItineraraio(Itinerario x){
        return new ResItinerarioDto(
                x.getId(),
                x.getIdVeiculo(),
                x.getIdterminalOrigen(),
                x.getIdterminalDestino(),
                x.getFechaSalida(),
                x.getFechaLLegada(),
                x.getCosto(),null,null,null,null
        );
    }
}
