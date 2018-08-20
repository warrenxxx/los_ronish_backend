/**
 * The User class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :20/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.models;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
@Accessors(chain = true)
public class User {
    private String user;
    private String password;
    private String nombre;
    private String email;
    private String sexo;


}
