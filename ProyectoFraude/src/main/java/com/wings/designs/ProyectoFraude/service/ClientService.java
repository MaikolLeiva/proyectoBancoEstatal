/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.service;

import com.wings.designs.ProyectoFraude.persistence.model.Client;
import com.wings.designs.ProyectoFraude.persistence.model.Role;
import com.wings.designs.ProyectoFraude.persistence.model.User;
import com.wings.designs.ProyectoFraude.persistence.repository.ClientRepository;
import com.wings.designs.ProyectoFraude.persistence.repository.RoleRepository;
import com.wings.designs.ProyectoFraude.registration.RegistrationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientService(ClientRepository clientRepository, UserService userService, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id){
        return clientRepository.findClientById(id);
    }

    public void addNewClient(RegistrationRequest registrationRequest) {

        Optional<Client> clientOptional =
                clientRepository.findClientsByRut(registrationRequest.getRut());
        Optional<Client> clientOptional2 =
                clientRepository.findClientsByAccount(registrationRequest.getAccount());
        Optional<Client> clientOptional3 =
                clientRepository.findClientsByEmail(registrationRequest.getEmail());
        Optional<User> userOptional=
                userService.getUserByRut(registrationRequest.getRut());
        if (!clientOptional.isPresent() && !clientOptional2.isPresent() && !clientOptional3.isPresent() &&
                !userOptional.isPresent()) {
            User userForNewClient = new User(registrationRequest.getRut(),
                    passwordEncoder.encode(registrationRequest.getPassword()),
                    roleRepository.findByName(Role.enumRole.ROLE_CLIENT));
            Client newClient = new Client(registrationRequest.getRut(), registrationRequest.getFullName(),
                    registrationRequest.getAddress(), registrationRequest.getEmail(), registrationRequest.getAccount(),
                    registrationRequest.getPhoneNumber(),userForNewClient);
            clientRepository.save(newClient);
            return;
        }
        if (clientOptional2.isPresent()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "account number not available");
        }
        if (clientOptional3.isPresent()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "email not available");
        }
        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "rut already taken");
    }
}
