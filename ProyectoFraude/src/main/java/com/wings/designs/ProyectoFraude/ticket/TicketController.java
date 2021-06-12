package com.wings.designs.ProyectoFraude.ticket;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/ticket")
public class TicketController {
    public final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> getTickets(){
        return ticketService.getTickets();

    }

    @GetMapping("/available")
    public List<Ticket> getTicketsAvailable(){
        return ticketService.getTicketsAvailable();

    }
}
