/**
 * The BoletoDto class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :21/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class BoletoDto {
    public Date fechaVenta;
    public Date fechaEntrega;
    public Date fechaSalida;

}
