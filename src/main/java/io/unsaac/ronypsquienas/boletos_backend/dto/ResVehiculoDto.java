/**
 * The ResVehiculoDto class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :25/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.dto;

import com.sun.deploy.perf.PerfRollup;
import io.unsaac.ronypsquienas.boletos_backend.models.Person;
import io.unsaac.ronypsquienas.boletos_backend.models.Vehiculo.Asiento;
import io.unsaac.ronypsquienas.boletos_backend.models.Vehiculo.TipoVeiculo;
import io.unsaac.ronypsquienas.boletos_backend.models.Vehiculo.Vehiculo;
import io.unsaac.ronypsquienas.boletos_backend.utils.Ref;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Document
@Accessors(chain = true)

public class ResVehiculoDto extends Vehiculo {
    @Getter
    @Setter
    public Person[] terramozas;
    @Getter
    @Setter
    public Person[] choferes;

    @Getter
    @Setter

    public String[] typos;

    public ResVehiculoDto(String id, String modelo, Integer NroAsientos, TipoVeiculo tipoVeiculo, Ref[] idPersonaTerramozas, Ref[] idPersonaChoferes, Asiento[] asientos, Person[] terramozas, Person[] choferes, String[] typos) {
        super(id, modelo, NroAsientos, tipoVeiculo, idPersonaTerramozas, idPersonaChoferes, asientos);
        this.terramozas = terramozas;
        this.choferes = choferes;
        this.typos = typos;
    }
}
