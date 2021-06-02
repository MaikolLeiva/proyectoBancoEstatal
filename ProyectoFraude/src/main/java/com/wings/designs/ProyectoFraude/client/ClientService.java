package com.wings.designs.ProyectoFraude.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients(){
        return clientRepository.findAll();

    }

    public void addNewClient(Client client) {
        Optional<Client> clientOptional =
                clientRepository.findClientByMail(client.getMail());
        Optional<Client> clientOptional2 =
                clientRepository.findClientByAccountNumber(client.getAccountNumber());
        if (clientOptional.isPresent()){
            throw new IllegalStateException("Email tomado");
        }
        if (clientOptional2.isPresent()){
            throw new IllegalStateException("Numero de Cuenta ya existente");
        }
        clientRepository.save(client);

    }
}
