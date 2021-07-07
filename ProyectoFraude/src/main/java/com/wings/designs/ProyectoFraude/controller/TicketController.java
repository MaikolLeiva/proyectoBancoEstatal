/*
 * Copyright (c) 2021. Wings Design.
 */
package com.wings.designs.ProyectoFraude.controller;

import com.wings.designs.ProyectoFraude.persistence.model.Ticket;
import com.wings.designs.ProyectoFraude.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    @RequestMapping(
            value = "/pending",
            params = { "userId" },
            method = RequestMethod.GET)
    @ResponseBody
    public String getTicketsPending(
            @RequestParam("userId") long id) {
        return "Narrow Get a specific Bar with id=" + id;
    }
}
