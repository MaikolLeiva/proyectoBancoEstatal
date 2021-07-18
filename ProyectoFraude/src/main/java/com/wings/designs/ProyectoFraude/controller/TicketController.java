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

/**
 * The rest Controller in charge of handling the
 * {@link Ticket ticket} resource on the API.
 * @author Nicolas Henriquez
 * @author Sebastian Zapata
 * @author Ignacio Cabrera
 * @author Maikol Leiva
 * @version 1.0
 */
@RestController
@RequestMapping("/tickets")
public class TicketController {

    /**
     * service that allow to take data from
     * the ticket resource.
     */
    public final TicketService ticketService;

    /**
     * Main constructor.
     * @param ticketService service that allow to take data from
     *                      the ticket resource. It's autowired.
     */
    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /**
     * Give all the tickets that are present on the API database.
     * @return A list of all the tickets on the API, if there's no tickets
     * then give an empty list.
     */
    @GetMapping
    public List<Ticket> getTickets() {
        return ticketService.getTickets();
    }

    /**
     * Give all the tickets that are still with an Open Status.
     * @return A list with all the Tickets that are available to
     * be taken by the managers. If there's no ticket with that status, then gives
     * an empty list
     */
    @GetMapping("/available")
    public List<Ticket> getTicketsAvailable() {
        return ticketService.getTicketsAvailable();
    }

    /**
     * Give all the ticket that are still under review by a manager.
     * @return A list with all the Tickets that are under review
     * by the managers. If there's no ticket with that status, then gives
     * an empty list
     */
    @GetMapping("/pending")
    public List<Ticket> getTicketsTaken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userRut = (String) auth.getPrincipal();
        return ticketService.getTicketsTakenByManager(userRut);
    }

    /**
     * Create a ticket with the given information in the Request.
     * @param request An object that has all the attributes needed
     *                to create a new ticket on the system.
     */
    @PostMapping("/create")
    public void registerNewTicket(@RequestBody NewTicketRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userRut = (String) auth.getPrincipal();
        ticketService.addNewTicket(request, userRut);
    }

    /**
     * Given a ticket id, change the manager in charge of that ticket
     * with the one that made the request, an also changes the status of
     * the ticket to a pending one.
     * @param ticketId It's the id of the ticket that the manager
     *                 wants to take under review.
     */
    @PatchMapping("/me/manager/")
    public void takeTicket(@RequestParam(name = "id") Long ticketId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userRut = (String) auth.getPrincipal();
        ticketService.takeTicket(userRut, ticketId);

    }

    /**
     * Given a ticket id, change the status of that ticket to close.
     * The ticket needs to be in charge of the manager that made the request.
     * @param ticketId It's the id of the ticket that the manager wants
     *                 to change to a close status.
     */
    @PatchMapping("/me/status/")
    public void closeTicket(@RequestParam(name = "id") Long ticketId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userRut = (String) auth.getPrincipal();
        ticketService.closeTicket(userRut, ticketId);
    }


}
