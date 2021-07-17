/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.controller;

import com.wings.designs.ProyectoFraude.requestbody.RegistrationRequest;
import com.wings.designs.ProyectoFraude.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegistrationController {
    final ClientService clientService;

    @Autowired
    public RegistrationController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public void registerNewUser(@Valid @RequestBody RegistrationRequest registrationRequest) {
        clientService.addNewClient(registrationRequest);
    }

}
