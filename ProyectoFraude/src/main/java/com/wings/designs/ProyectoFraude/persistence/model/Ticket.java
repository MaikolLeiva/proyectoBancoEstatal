/*
 * Copyright (c) 2021. Wings Design.
 */
package com.wings.designs.ProyectoFraude.persistence.model;

import javax.persistence.*;

/**
 * Represents a ticket for a fraudulent case made for an user.
 *
 * @author Nicolas Henriquez
 * @author Sebastian Zapata
 * @author Ignacio Cabrera
 * @author Maikol Leiva
 * @version 1.0
 */
@Entity(name = "Ticket")
public class Ticket {
    /**
     * It's the identifier of the ticket on the database.
     */
    @Id
    @SequenceGenerator(name = "ticket_sequence", sequenceName =
            "ticket_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "ticket_sequence")
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * It's the name of the type of card assigned with the ticket.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "card_type")
    private enumTypesOfCards cardType;

    /**
     * It's the comment made by the client about the ticket.
     */
    @Column(name = "comment", updatable = false)
    private String comment;

    /**
     * It's the current status of the ticket.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private enumStatesOfTicket status;

    /**
     * It's the client that made the ticket.
     */
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    /**
     * It's the manager that took the ticket.
     */
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    /**
     * Empty Constructor.
     */
    public Ticket() {
        // Nothing here.
    }

    /**
     * Main constructor that receives all attributes as parameters,
     * except for the id and managerRut.
     *
     * @param client   Is the id of the user that made the ticket.
     * @param cardType Is the type of the card that was affected
     *                 by the fraud.
     * @param comment  Is a comment given by the client that contains a
     *                 little info about the incident.
     * @param status   Is the current status of the ticket.
     */
    public Ticket(final enumTypesOfCards cardType, final String comment,
                  final enumStatesOfTicket status, final Client client) {
        this.cardType = cardType;
        this.comment = comment;
        this.status = status;
        this.client = client;
        this.manager = null;
    }

    /**
     * Returns the id of the ticket.
     *
     * @return the id of the ticket.
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the type of card associated with the ticket.
     *
     * @return the type of card.
     */
    public enumTypesOfCards getCardType() {
        return cardType;
    }

    /**
     * Returns the comment made by the client when he made the ticket.
     *
     * @return the comment made by the client.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Returns the current status of the ticket.
     *
     * @return the current status of the ticket.
     */
    public enumStatesOfTicket getStatus() {
        return status;
    }

    /**
     * Returns the client that made the ticket.
     *
     * @return the client who made the ticket.
     */
    public Client getClient() {
        return client;
    }

    /**
     * Returns the manager who took the ticket.
     *
     * @return the manager who took the ticket. If the ticket has no manager
     * assigned yet, then returns null.
     */
    public Manager getManager() {
        return manager;
    }

    public void setStatus(enumStatesOfTicket status) {
        this.status = status;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }


    /**
     * Enum that defines all the possibles states of the ticket.
     */
    public enum enumStatesOfTicket {
        /**
         * This status means that the ticket haven't been taken by a manager.
         */
        OPEN,

        /**
         * This status means that the ticket was taken by a manager
         * but still under review.
         */
        PENDING,

        /**
         * This status means that the ticket was finally resolved by a manager.
         */
        CLOSED
    }

    /**
     * Enum that defines the possibles types of a bank's card.
     */
    public enum enumTypesOfCards {
        /**
         * Represents that the card is a debit card of the bank.
         */
        CREDIT,

        /**
         * Represents that the card is a credit card of the bank.
         */
        DEBIT
    }
}
