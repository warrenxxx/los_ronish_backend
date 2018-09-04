package io.unsaac.ronypsquienas.boletos_backend;

import io.unsaac.ronypsquienas.boletos_backend.Service.ReservaService;
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
    CommandLineRunner commandLineRunner2(ReservaService dao) {
        return args -> {
            dao.findAll()
                    .subscribe(System.out::println);
        };
    }
}
