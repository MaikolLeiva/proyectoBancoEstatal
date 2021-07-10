/*
 * Copyright (c) 2021. Wings Design.
 */
package com.wings.designs.ProyectoFraude.persistence.repository;

import com.wings.designs.ProyectoFraude.persistence.model.Client;
import com.wings.designs.ProyectoFraude.persistence.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 *Layer that manages the requests made for the database that are related to the entity User.
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    /**
     * Search for the ticket's that have a determined status, and return all the matches.
     * @param status is the status of the tickets wanted.
     * @return A list of all the {@link Ticket Tickets} with the status defined.
     * If there's no tickets with that status, returns an empty list.
     */
    @Query("FROM Ticket  WHERE status=?1")
    List<Ticket> findTicketByStatus(Ticket.enumStatesOfTicket status);
    /**
     * Search for all the tickets that have the specified rut as it's manager rut. Returns all matches.
     * @param rut Is the rut of the manager that is responsible for the ticket.
     * @return A list of all the {@link Ticket Tickets} that are managed by
     * the manager with the rut defined.If there's no tickets, returns an empty list.
     */
    @Query("FROM Ticket  WHERE manager=?1 order by id")
    List<Ticket> findTicketByManagerRut(String rut);

    @Query("FROM Ticket WHERE cardType=?1 AND status<>?2 AND client.id = :#{#client.id}")
    Optional<Ticket> findTicketByCardTypeAndClientAndStatusNotLike(Ticket.enumTypesOfCards cardType,
                                                                   Ticket.enumStatesOfTicket state,
                                                                   @Param("client")  Client client);


}
