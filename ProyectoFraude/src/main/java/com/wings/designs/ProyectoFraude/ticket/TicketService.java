/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.ticket;

import com.wings.designs.ProyectoFraude.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserService userService;

    public TicketService(TicketRepository ticketRepository, UserService userService) {
        this.ticketRepository = ticketRepository;
        this.userService = userService;
    }


    public List<Ticket> getTickets(){

        return ticketRepository.findAll();
    }
    public List<Ticket> getTicketsAvailable(){

        return ticketRepository.findTicketByStatus("open");
    }
    public List<Ticket> getTicketsByManager(String managerRut){

        return ticketRepository.findTicketByManagerRut(managerRut);
    }

    public void addNewTicket(Ticket ticket){


    }
}
