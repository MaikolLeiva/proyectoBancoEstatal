/*
 * Copyright (c) 2021. Wings Design.
 */
package com.wings.designs.ProyectoFraude.service;

import com.wings.designs.ProyectoFraude.controller.UserController;
import com.wings.designs.ProyectoFraude.persistence.model.User;
import com.wings.designs.ProyectoFraude.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
/**
 * Serves as the layer that is behind the logic for the requests made in the
 * {@link UserController RestController} and also communicates with the
 * {@link UserRepository Repository layer}.
 */
@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * Main constructor.
     * @param userRepository An object of the class
     * {@link UserRepository UserRepository} that is needed to communicate with
     * the database.
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Return a list of all users in the system.
     * @return If there's users in the system return a list of
     * {@link User User}. If there's no users in the system, returns an empty
     * list
     */
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    /**
     * Look for user with a specified id, if found, returns it.
     * @param id Is the Id of the {@link User User}
     * @return If the user exists return the {@link User User},
     * otherwise returns null.
     */
    public Optional<User> getUserById(Long id){
        return userRepository.findUsersById(id);
    }
    public Optional<User> findUserByRut(String rut){
        return userRepository.findUsersByRut(rut);
    }
    public User getUsersByRut(String rut){
        return userRepository.getUserByRut(rut);
    }
    /**
     * Take a instance of {@link User User} and before adding it to the system
     * look if other users has the same RUT or the same email, if that's the case the user is not put in to the system.
     * Otherwise, the user is added.     *
     * @param user An instance of {@link User User}.
     * @throws IllegalStateException If the user to be added has a RUT that other user has already taken in the system.
     * @throws IllegalStateException If the user to be added has an email that other user has already in the system.
     */
    public void addNewUser(User user) {
        Optional<User> usersOptional =
                userRepository.findUsersByRut(user.getRut());
        if (usersOptional.isPresent()) {
            throw new IllegalStateException("Rut tomado");
        }
        userRepository.save(user);
    }
}
