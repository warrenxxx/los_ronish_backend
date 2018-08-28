/**
 * The Destino class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :23/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.models.Vehiculo;

import io.unsaac.ronypsquienas.boletos_backend.utils.Ref;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Document
public class Vehiculo{
    public String id;
    public String modelo;
    public Integer NroPisos;
    public TipoVeiculo tipoVeiculo;
    public Ref[] idPersonaTerramozas;
    public Ref[] idPersonaChoferes;
    public Asiento[] asientos;
    public String img;
}

