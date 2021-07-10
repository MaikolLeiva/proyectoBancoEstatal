/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.service;

import com.wings.designs.ProyectoFraude.persistence.model.Client;
import com.wings.designs.ProyectoFraude.persistence.model.Ticket;
import com.wings.designs.ProyectoFraude.persistence.model.User;
import com.wings.designs.ProyectoFraude.persistence.repository.TicketRepository;
import com.wings.designs.ProyectoFraude.requestbody.NewTicketRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final ClientService clientService;
    private final UserService userService;

    public TicketService(TicketRepository ticketRepository, ClientService clientService, UserService userService) {
        this.ticketRepository = ticketRepository;
        this.clientService = clientService;
        this.userService = userService;
    }


    public List<Ticket> getTickets(){

        return ticketRepository.findAll();
    }
    public List<Ticket> getTicketsAvailable(){

        return ticketRepository.findTicketByStatus(Ticket.enumStatesOfTicket.OPEN);
    }
    public List<Ticket> getTicketsByManager(String managerRut){

        return ticketRepository.findTicketByManagerRut(managerRut);
    }

    public void addNewTicket(NewTicketRequest ticketRequest, String userRut){
        User user = userService.getUsersByRut(userRut);
        Client client = clientService.getClientByUser(user);
        Optional<Ticket> optionalTicket =
                ticketRepository.findTicketByCardTypeAndClientAndStatusNotLike(ticketRequest.getCardType(),
                        Ticket.enumStatesOfTicket.CLOSED, client);
        if(optionalTicket.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "A ticket with that card is still processing");
        }
        Ticket ticket = new Ticket(ticketRequest.getCardType(), ticketRequest.getComment(),
                Ticket.enumStatesOfTicket.OPEN, client);
        ticketRepository.save(ticket);

    }
}
