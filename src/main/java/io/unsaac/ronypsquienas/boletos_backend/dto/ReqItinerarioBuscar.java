/**
 * The ReqItinerarioBuscar class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :27/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
@Accessors(chain = true)
public class ReqItinerarioBuscar {
    private String origen;
    private String destino;
    private Date fecha;
}
