package com.wings.designs.ProyectoFraude.client;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/Client")
public class ClientController {


    public final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {

        this.clientService=clientService;
    }

    @GetMapping
    public List<Client> getClients(){
        return clientService.getClients();

    }
    @PostMapping
    public void registerNewClient(@RequestBody Client client){
        clientService.addNewClient(client);
    }
}
