/**
 * The ResLoginDto class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :22/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.dto;

import io.unsaac.ronypsquienas.boletos_backend.models.Person;
import io.unsaac.ronypsquienas.boletos_backend.models.User;
import io.unsaac.ronypsquienas.boletos_backend.utils.Ref;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Document
@Accessors(chain = true)
public class ResLoginDto extends User {

    @Getter
    @Setter
    private Person person;

    public ResLoginDto(String id, String user, String password, String role, Ref idPerson, Person person) {
        super(id, user, password, role, idPerson);
        this.person = person;
    }
}
