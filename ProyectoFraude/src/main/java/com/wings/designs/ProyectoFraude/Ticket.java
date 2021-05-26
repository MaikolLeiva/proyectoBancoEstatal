package com.wings.designs.ProyectoFraude;

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
            name="tipo_tarjeta",
            updatable = false,
            nullable = false,
            columnDefinition = "TEXT"

    )
    private String cardType;
    @Column(
            name="Motivo",
            updatable = false
    )
    private String comment;

    public Ticket(Long id, String cardType, String comment) {
        this.id = id;
        this.cardType = cardType;
        this.comment = comment;
    }

    public Ticket() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", cardType='" + cardType + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
