/*
 * Copyright (c) 2021. Wings Design.
 */
package com.wings.designs.ProyectoFraude.ticket;

import javax.persistence.*;

/**
 * Represents a ticket for a fraudulent case made for an user.
 */
@Entity(name= "Ticket")
public class Ticket {
    @Id
    @SequenceGenerator(name="ticket_sequence",sequenceName = "ticket_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ticket_sequence")
    @Column(name="codigo_caso",updatable = false)
    private Long id;
    @Column(name="client_rut",updatable = false,nullable = false)
    private String clientRut;
    @Column(name="manager_rut")
    private String managerRut;
    @Column(name="card_type")
    private enumTypesOfCards cardType;
    @Column(name="comment",updatable = false)
    private String comment;
    @Column(name="status",nullable = false)
    private enumStatesOfTicket status;
    /**
     * Main constructor that receives all attributes as parameters, except for the id and managerRut.
     * @param clientRut Is the rut of the user that made the ticket.
     * @param cardType Is the type of the card that was affected by the fraud.
     * @param comment Is a comment given by the client that contains a little info about the incident.
     * @param status Is the current status of the ticket.
     */
    public Ticket(String clientRut, enumTypesOfCards cardType, String comment, enumStatesOfTicket status) {
        this.managerRut=null;
        this.clientRut = clientRut;
        this.cardType = cardType;
        this.comment = comment;
        this.status = status;
    }

    /**
     * Empty Constructor.
     */
    public Ticket() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getClientRut() {
        return clientRut;
    }
    public void setClientRut(String clientRut) {
        this.clientRut = clientRut;
    }
    public String getManagerRut() {
        return managerRut;
    }
    public void setManagerRut(String managerRut) {
        this.managerRut = managerRut;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public enumStatesOfTicket getStatus() {
        return status;
    }

    public void setStatus(enumStatesOfTicket status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", clientRut='" + clientRut + '\'' +
                ", managerRut='" + managerRut + '\'' +
                ", cardType='" + cardType + '\'' +
                ", comment='" + comment + '\'' +
                ", status='" + status + '\'' +
                '}';
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
    enum enumTypesOfCards{
        CREDIT,
        DEBIT
    }
}
