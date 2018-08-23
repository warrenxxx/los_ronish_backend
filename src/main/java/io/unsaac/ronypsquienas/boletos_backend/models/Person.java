/**
 * The Person class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :21/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
@Accessors(chain = true)
public class Person {
    private String id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String dni;
    private String telefono;
    private String celular;
    private String email;
    private String idImage;
}
