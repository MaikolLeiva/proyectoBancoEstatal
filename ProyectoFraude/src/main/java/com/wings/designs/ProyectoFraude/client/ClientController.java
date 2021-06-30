/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.client;

import com.wings.designs.ProyectoFraude.registration.RegistrationRequest;
import com.wings.designs.ProyectoFraude.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/client")
public class ClientController {
    public final ClientService clientService;
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping
    public List<Client> getClients() {
        return clientService.getClients();
    }
    @PostMapping("/create")
    public void registerNewUser(@RequestBody RegistrationRequest registrationRequest) {
        clientService.addNewClient(registrationRequest);
    }

}
