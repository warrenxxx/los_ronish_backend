/**
 * The ComboBoxResponse class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :25/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ComboBoxResponse {
    @Id
    private String id;
    private String desc;
}
