/**
 * The Venta class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :23/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.models;

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
public class Venta {
    private String id;
    private Ref idPasajero;
    private Ref idVendedor;
    private Ref idReserva;
    private Boleta boleta;
    private Encomienda[] encomiendas;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    public class Boleta{
        private String codBoleta;
        private Double total;
        private Double fechaEmicion;
        private String descripcion;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    public class Encomienda{
        private String id;
        private Ref idTerminaOrigen;
        private Ref idTerminaDestino;
        private Ref idUserRemitente;
        private Ref idPersonDestinatario;
        private Ref idVehiculo;
        private Double precio;
        private Double descuento;
        private Date fechaRegistro;
    }
}
