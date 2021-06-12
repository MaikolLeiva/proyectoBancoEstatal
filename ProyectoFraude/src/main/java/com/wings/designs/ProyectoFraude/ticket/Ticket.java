package com.wings.designs.ProyectoFraude.ticket;

import javax.persistence.*;

@Entity(name= "Ticket")
public class Ticket {
    @Id
    @SequenceGenerator(
            name="ticket_sequence",
            sequenceName = "ticket_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ticket_sequence"

    )
    @Column(
            name="codigo_caso",
            updatable = false

    )
    private Long id;
    @Column(
            name="client_rut",
            updatable = false,
            nullable = false

    )
    private String clientRut;
    @Column(
            name="manager_rut"
    )
    private String managerRut;
    @Column(
            name="card_type"

    )
    private String cardType;

    @Column(
            name="comment",
            updatable = false
    )
    private String comment;
    @Column(
            name="status",
            nullable = false
    )
    private String status;


    public Ticket(String clientRut, String cardType, String comment, String status) {
        this.managerRut=null;
        this.clientRut = clientRut;
        this.cardType = cardType;
        this.comment = comment;
        this.status = status;
    }

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

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
}
