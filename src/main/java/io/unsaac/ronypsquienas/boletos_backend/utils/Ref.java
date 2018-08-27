/**
 * The Ref class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :21/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Ref {
    @Id
    public String id;
    public String collection;
    public static Ref PersonRef(String id){
        return new Ref().setCollection("person").setId(id);
    }

    public static Ref VehiculoRef(String id) {
        return new Ref().setCollection("vehiculo").setId(id);
    }

    public static Ref TerminalRef(String s) {
        return new Ref().setCollection("terminal").setId(s);
    }

    public static Ref ItinerarioRef(String x) {
        return new Ref().setCollection("itinerario").setId(x);
    }
}
