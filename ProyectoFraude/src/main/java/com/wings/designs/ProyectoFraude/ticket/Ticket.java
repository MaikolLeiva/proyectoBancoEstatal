/*
 * Copyright (c) 2021. Wings Design.
 */
package com.wings.designs.ProyectoFraude.ticket;

import com.wings.designs.ProyectoFraude.client.Client;
import com.wings.designs.ProyectoFraude.manager.Manager;

import javax.persistence.*;

/**
 * Represents a ticket for a fraudulent case made for an user.
 */
@Entity(name = "Ticket")
public class Ticket {
    @Id
    @SequenceGenerator(name = "ticket_sequence", sequenceName = "ticket_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_sequence")
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "card_type")
    private enumTypesOfCards cardType;
    @Column(name = "comment", updatable = false)
    private String comment;
    @Column(name = "status", nullable = false)
    private enumStatesOfTicket status;
    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;
    @ManyToOne
    @JoinColumn(name="manager_id")
    private Manager manager;

    /**
     * Empty Constructor.
     */
    public Ticket() {
    }

    /**
     * Main constructor that receives all attributes as parameters, except for the id and managerRut.
     * @param client Is the id of the user that made the ticket.
     * @param cardType Is the type of the card that was affected by the fraud.
     * @param comment Is a comment given by the client that contains a little info about the incident.
     * @param status Is the current status of the ticket.
     */
    public Ticket(enumTypesOfCards cardType, String comment, enumStatesOfTicket status, Client client) {
        this.cardType = cardType;
        this.comment = comment;
        this.status = status;
        this.client = client;
        this.manager = null;
    }

    public Long getId() {
        return id;
    }

    public enumTypesOfCards getCardType() {
        return cardType;
    }

    public String getComment() {
        return comment;
    }

    public enumStatesOfTicket getStatus() {
        return status;
    }

    public Client getClient() {
        return client;
    }

    public Manager getManager() {
        return manager;
    }

    /**
     * Enum that defines all the possibles states of the ticket.
     * <code>OPEN<code/> means that the ticket haven't been taken by a manager.
     * <code>PENDING<code/> means that the ticket was taken by a manager but still under review.
     * <code>CLOSED<code/> means that the ticket was finally resolved by a manager.
     */
    enum enumStatesOfTicket{
        OPEN,
        PENDING,
        CLOSED
    }
    /**
     * Enum that defines the possibles types of a bank's card.
     * <code>DEBIT<code/> Represents that the card is a debit card of the bank.
     * <code>CREDIT<code/> Represents that the card is a credit card of the bank.
     */
    enum enumTypesOfCards {
        CREDIT,
        DEBIT
    }
}
