/**
 * The CostosAsientosItinerario class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :26/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.models.Itinerario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CostosAsientosItinerario {
    public String id;
    public String tipoAsiento;
    public String costo;

}
