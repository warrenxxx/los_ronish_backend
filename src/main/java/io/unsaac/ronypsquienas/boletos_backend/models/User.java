/**
 * The User class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :20/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.models;

import io.unsaac.ronypsquienas.boletos_backend.utils.Ref;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {
    public String id;
    @Indexed(unique = true)
    private String user;
    private String password;
    private String role;
    private Ref idPerson;
}
