/*
 * Copyright (c) 2021. Wings Design.
 */
package com.wings.designs.ProyectoFraude.controller;

import com.wings.designs.ProyectoFraude.persistence.model.Ticket;
import com.wings.designs.ProyectoFraude.requestbody.NewTicketRequest;
import com.wings.designs.ProyectoFraude.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tickets")
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

    @RequestMapping(
            value = "/pending",
            params = { "userId" },
            method = RequestMethod.GET)
    @ResponseBody
    public String getTicketsPending(
            @RequestParam("userId") long id) {
        return "Narrow Get a specific Bar with id=" + id;
    }
    @PostMapping("/create/")
    public void registerNewTicket(@RequestBody NewTicketRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userRut = (String) auth.getPrincipal();
        ticketService.addNewTicket(request, userRut);
    }

}
