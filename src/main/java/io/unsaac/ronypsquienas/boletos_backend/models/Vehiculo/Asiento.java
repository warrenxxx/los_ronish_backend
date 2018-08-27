/**
 * The Asiento class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :24/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.models.Vehiculo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Asiento{
    public Integer num;
    public String type;
}
