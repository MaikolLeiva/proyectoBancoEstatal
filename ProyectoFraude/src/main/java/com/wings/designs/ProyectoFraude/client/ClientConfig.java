package com.wings.designs.ProyectoFraude.client;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class ClientConfig {

    @Bean
    CommandLineRunner commandLineRunner(ClientRepository repository) {
        return args -> {
            Client testClient = new Client("123123123-K",
                    "Angelica",
                    "sadasdasdsad",
                    "asdaskldjlk@dsadsad.cl",
                    1L
            );
            Client testClient2 = new Client("asdasdsa-K",
                    "Ryo",
                    "xczxczx",
                    "sadadddd@dsadsad.cl",
                    2L
            );
            repository.saveAll(
                    Arrays.asList(testClient,testClient2)

            );

        };

    }
}
