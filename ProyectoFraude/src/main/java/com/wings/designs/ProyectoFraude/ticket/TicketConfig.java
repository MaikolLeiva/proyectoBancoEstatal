/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.ticket;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class TicketConfig {

    /**
     * Initialize the system with a few tickets in the database.
     * @param repository An object from the class
     * {@link com.wings.designs.ProyectoFraude.ticket.TicketRepository TicketRepository} that allows to use the business
     * layer for the class {@link com.wings.designs.ProyectoFraude.ticket.Ticket Ticket}.
     * @return Code sentences to create multiple tickets,that are executed after the application started.
     */
    @Bean
    CommandLineRunner commandLineRunnerTicket (TicketRepository repository){

        return args -> {
            Ticket testTicket = new Ticket("2123123123-k",Ticket.enumTypesOfCards.CREDIT,"hasdadadsad",
                    Ticket.enumStatesOfTicket.OPEN);
            Ticket testTicket2 = new Ticket("213123-k",Ticket.enumTypesOfCards.DEBIT,"xczxc",
                    Ticket.enumStatesOfTicket.CLOSED);
            Ticket testTicket3 = new Ticket("421414-k",Ticket.enumTypesOfCards.DEBIT,"cccvvz",
                    Ticket.enumStatesOfTicket.PENDING);
            Ticket testTicket4 = new Ticket("2525256-k",Ticket.enumTypesOfCards.DEBIT,"jsakdjlasdj",
                    Ticket.enumStatesOfTicket.OPEN);
            repository.saveAll(Arrays.asList(testTicket, testTicket2, testTicket3, testTicket4));
        };
    }
}
