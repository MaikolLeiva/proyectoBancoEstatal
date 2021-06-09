package com.wings.designs.ProyectoFraude.users;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class UsersConfig {

    @Bean
    CommandLineRunner commandLineRunner2(UsersRepository repository) {
        return args -> {
            Users testUser = new Users("123123123-K",
                    "password",
                    "Vin The Hero of Ages",
                    "MistBorn 1223",
                    "aasdasdasd@san.cl",
                    1L
            );
            Users testUser2 = new Users("225255446-8",
                    "1234",
                    "Kellsier ",
                    "MistBorn 3321",
                    "kel@lord.cl",
                    2L
            );
            repository.saveAll(
                    Arrays.asList(testUser,testUser2)

            );

        };

    }

}
