/**
 * The Itinerario class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :23/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.models.Itinerario;

import io.unsaac.ronypsquienas.boletos_backend.utils.Ref;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Document
public class Itinerario {
    private String id;
    private Ref idVeiculo;
    private Ref idterminalOrigen;
    private Ref idterminalDestino;
    private Date fechaSalida;
    private Date fechaLLegada;
    private Double costo;
}
