/**
 * The ReqRegisterDto class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :21/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.dto;

import io.unsaac.ronypsquienas.boletos_backend.models.Person;
import io.unsaac.ronypsquienas.boletos_backend.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ReqRegisterDto {
    private String user;
    private String password;
    private String dni;
    private String nombre;
    private String apellido;
    private String email;
    private String direccion;
    private String sexo;
    private String idImage;
    private String telefono;
    private String celular;
    public User getUser(){
        return new User(
                new ObjectId().toString(),
                this.user,
                this.password,
                "user",
                null
                );
    }
    public Person getPerson(){
        return new Person(
                new ObjectId().toString(),
                nombre,apellido,direccion,dni,telefono,celular,email,idImage
        );
    }
}
