package com.wings.designs.ProyectoFraude.ticket;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class TicketConfig {


    @Bean
    CommandLineRunner commandLineRunnerTicket (TicketRepository repository){

        return args -> {

            Ticket testTicket = new Ticket(
                    "2123123123-k",
                    "credit",
                    "hasdadadsad",
                    "open"

            );
            Ticket testTicket2 = new Ticket(
                    "213123-k",
                    "debit",
                    "xczxc",
                    "Pending"

            );
            Ticket testTicket3 = new Ticket(
                    "421414-k",
                    "debit",
                    "cccvvz",
                    "Pending"

            );
            Ticket testTicket4 = new Ticket(
                    "2525256-k",
                    "debit",
                    "jsakdjlasdj",
                    "open"

            );

            repository.saveAll(
                    Arrays.asList(testTicket, testTicket2, testTicket3, testTicket4)
            );




        };
    }
}
