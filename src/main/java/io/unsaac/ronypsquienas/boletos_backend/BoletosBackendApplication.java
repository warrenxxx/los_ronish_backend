package io.unsaac.ronypsquienas.boletos_backend;

import io.unsaac.ronypsquienas.boletos_backend.dao.ItinerarioDao;
import org.bson.types.ObjectId;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BoletosBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoletosBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner2(ItinerarioDao dao) {
        return args -> {
            System.out.println("www");

            dao.findAllByIdterminalOrigen_IdAndIdterminalDestino_Id(
                    new ObjectId("5b81f6c3ee69ac283cf5896d"),
                new ObjectId("5b81f6c9ee69ac283cf5896e")
            )
                    .subscribe(System.out::println);
        };
    }
}
