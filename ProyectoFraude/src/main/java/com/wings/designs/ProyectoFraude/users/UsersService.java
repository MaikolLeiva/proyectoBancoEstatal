/*
 * Copyright (c) 2021. Wings Design.
 */
package com.wings.designs.ProyectoFraude.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
/**
 * Serves as the layer that is behind the logic for the requests made in the
 * {@link com.wings.designs.ProyectoFraude.users.UsersController RestController} and also communicates with the
 * {@link com.wings.designs.ProyectoFraude.users.UsersRepository Repository layer}.
 */
@Service
public class UsersService {
    private final UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * Main constructor.
     * @param usersRepository An object of the class
     * {@link com.wings.designs.ProyectoFraude.users.UsersRepository UsersRepository} that is needed to communicate with
     * the database.
     */
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository=usersRepository;
    }

    /**
     * Return a list of all users in the system.
     * @return If there's users in the system return a list of
     * {@link com.wings.designs.ProyectoFraude.users.Users Users}. If there's no users in the system, returns an empty
     * list
     */
    public List<Users> getUsers(){
        return usersRepository.findAll();
    }
    /**
     * Look for user with a specified id, if found, returns it.
     * @param id Is the Id of the {@link com.wings.designs.ProyectoFraude.users.Users User}
     * @return If the user exists return the {@link com.wings.designs.ProyectoFraude.users.Users User},
     * otherwise returns null.
     */
    public Optional<Users> getUserById(Long id){
        return usersRepository.findUsersById(id);
    }
    /**
     * Take a instance of {@link com.wings.designs.ProyectoFraude.users.Users User} and before adding it to the system
     * look if other users has the same RUT or the same email, if that's the case the user is not put in to the system.
     * Otherwise, the user is added.     *
     * @param user An instance of {@link com.wings.designs.ProyectoFraude.users.Users User}.
     * @throws IllegalStateException If the user to be added has a RUT that other user has already taken in the system.
     * @throws IllegalStateException If the user to be added has an email that other user has already in the system.
     */
    public void addNewUser(Users user) {
        Optional<Users> usersOptional =
                usersRepository.findUsersByRutUser(user.getRut());
        Optional<Users> usersOptional2 =
                usersRepository.findUsersByEmailUser(user.getEmail());
        if (usersOptional.isPresent()){
            throw new IllegalStateException("Rut tomado");
        }
        if (usersOptional2.isPresent()){
            throw new IllegalStateException("Correo tomado");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
    }
}
