/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.service;

import com.wings.designs.ProyectoFraude.persistence.model.Client;
import com.wings.designs.ProyectoFraude.persistence.model.Role;
import com.wings.designs.ProyectoFraude.persistence.model.User;
import com.wings.designs.ProyectoFraude.persistence.repository.ClientRepository;
import com.wings.designs.ProyectoFraude.requestbody.RegistrationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public ClientService(ClientRepository clientRepository, RoleService roleService, PasswordEncoder passwordEncoder, UserService userService) {
        this.clientRepository = clientRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> findClientById(Long id){
        return clientRepository.findClientById(id);
    }

    public Client getClientById(Long id) {
        return clientRepository.getClientById(id);
    }

    public Client getClientByUser(User user) {
        return clientRepository.getClientByUser(user);
    }

    @Transactional
    public void addNewClient(RegistrationRequest registrationRequest) throws ConstraintViolationException {

        Optional<User> clientOptional =
                userService.findUserByRut(registrationRequest.getRut());
        Optional<Client> clientOptional2 =
                clientRepository.findClientsByAccount(registrationRequest.getAccount());
        Optional<Client> clientOptional3 =
                clientRepository.findClientsByEmail(registrationRequest.getEmail());
        if (clientOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Rut already taken");
        }
        if (clientOptional2.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "account number already taken");
        }
        if (clientOptional3.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "email taken");
        }

        User userForNewClient = new User(registrationRequest.getRut(),
                passwordEncoder.encode(registrationRequest.getPassword()),
                roleService.findRoleByName(Role.enumRole.ROLE_CLIENT));
        Client newClient = new Client(registrationRequest.getRut(), registrationRequest.getFullName(),
                registrationRequest.getAddress(), registrationRequest.getEmail(), registrationRequest.getAccount(),
                registrationRequest.getPhoneNumber(),userForNewClient);
        clientRepository.save(newClient);
    }
}
