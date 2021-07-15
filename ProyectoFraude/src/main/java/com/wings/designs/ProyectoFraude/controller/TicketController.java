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
import org.springframework.transaction.annotation.Transactional;
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
    public List<Ticket> getTickets() {
        return ticketService.getTickets();
    }

    @GetMapping("/available")
    public List<Ticket> getTicketsAvailable() {
        return ticketService.getTicketsAvailable();
    }

    @GetMapping("/pending")
    public List<Ticket> getTicketsTaken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userRut = (String) auth.getPrincipal();
        return ticketService.getTicketsTakenByManager(userRut);
    }

    @PostMapping("/create")
    public void registerNewTicket(@RequestBody NewTicketRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userRut = (String) auth.getPrincipal();
        ticketService.addNewTicket(request, userRut);
    }
    @Transactional
    @PutMapping("/me/manager/")
    public void takeTicket(@RequestParam(name = "id") Long ticketId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userRut = (String) auth.getPrincipal();
        ticketService.takeTicket(userRut, ticketId);

    }

}
