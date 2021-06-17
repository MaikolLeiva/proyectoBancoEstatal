/*
 * Copyright (c) 2021. Wings Design.
 *//*

package com.wings.designs.ProyectoFraude.users;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

*/
/**
 * A class to provides beans associated with the class {@link com.wings.designs.ProyectoFraude.user.User User}.
 * @author Nicolas Henriquez
 * @author Sebastian Zapata
 * @author Ignacio Cabrera
 * @author Maikol Leiva
 * @version 1.0
 *//*

@Configuration
public class UsersConfig {

    */
/**
     * Initialize the system with a few users in the database.
     * @param repository An object from the class
     * {@link com.wings.designs.ProyectoFraude.user.UserRepository UserRepository} that allows to use the business
     * layer for the class {@link com.wings.designs.ProyectoFraude.user.User User}.
     * @return Code sentences to create the users,that are executed after the application started .
     *//*

    @Bean
    CommandLineRunner commandLineRunner2(UserRepository repository) {
        return args -> {
            User testUser = new User("123123123-K","password","Vin The Hero of Ages",
                    "MistBorn 1223","aasdasdasd@san.cl",123121541L, 9989841L,
                    new Role());
            User testUser2 = new User("225255446-8","1234","Kellsier ","MistBorn 3321",
                    "kel@lord.cl",2L,59595512L,User.enumUserType.MANAGER);
            repository.saveAll(
                    Arrays.asList(testUser,testUser2)
            );
        };
    }
}
*/
