/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.controller;

import com.wings.designs.ProyectoFraude.persistence.model.Client;
import com.wings.designs.ProyectoFraude.service.ClientService;
import com.wings.designs.ProyectoFraude.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final TicketService ticketService;
    private final ClientService clientService;

    @Autowired
    public ClientController(final TicketService ticketService,
                            final ClientService clientService) {
        this.ticketService = ticketService;
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getClients() {
        return clientService.getClients();
    }


}
