/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.service;

import com.wings.designs.ProyectoFraude.persistence.model.Client;
import com.wings.designs.ProyectoFraude.persistence.model.Manager;
import com.wings.designs.ProyectoFraude.persistence.model.Ticket;
import com.wings.designs.ProyectoFraude.persistence.model.User;
import com.wings.designs.ProyectoFraude.persistence.repository.TicketRepository;
import com.wings.designs.ProyectoFraude.requestbody.NewTicketRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.plaf.PanelUI;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final ClientService clientService;
    private final UserService userService;
    private final ManagerService managerService;

    public TicketService(TicketRepository ticketRepository, ClientService clientService, UserService userService, ManagerService managerService) {
        this.ticketRepository = ticketRepository;
        this.clientService = clientService;
        this.userService = userService;
        this.managerService = managerService;
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

    public List<Ticket> getTicketsTakenByManager(String userRut) {
        User user = userService.getUsersByRut(userRut);
        Manager manager = managerService.getManagerByUser(user);
        return this.ticketRepository.getTicketByClientAndStatusLike(Ticket.enumStatesOfTicket.PENDING,
                manager);
    }

    @Transactional
    public void takeTicket(String userRut, Long ticketId) {
        User user = userService.getUsersByRut(userRut);
        Manager manager = managerService.getManagerByUser(user);
        Long numberOfTickets = ticketRepository.countByStatusAndManager(Ticket.enumStatesOfTicket.PENDING,
                manager);
        if (numberOfTickets>6) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }
        Ticket ticket = ticketRepository.getTicketById(ticketId);
        if (ticket==null){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    "ticket not found");
        }
        if (ticket.getStatus()!= Ticket.enumStatesOfTicket.OPEN) {
            throw  new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    "ticket is not open");
        }
        ticket.setStatus(Ticket.enumStatesOfTicket.PENDING);
        ticket.setManager(manager);
    }
    @Transactional
    public void closeTicket(String userRut, Long ticketId) {
        User user = userService.getUsersByRut(userRut);
        Manager manager = managerService.getManagerByUser(user);
        Ticket ticket = ticketRepository.getTicketById(ticketId);
        if (ticket==null){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    "ticket not found");
        }
        if (ticket.getStatus()!= Ticket.enumStatesOfTicket.PENDING) {
            throw  new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    "ticket is not under review");
        }
        if (ticket.getManager() == null) {
            throw  new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    "the ticket does not have any manager assigned");
        }
        if (!ticket.getManager().getId().equals(manager.getId())) {
            throw  new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    "the manager has not taken this ticket");
        }
        ticket.setStatus(Ticket.enumStatesOfTicket.CLOSED);
    }
}
