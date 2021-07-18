/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.requestbody;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wings.designs.ProyectoFraude.persistence.model.Ticket;

public class NewTicketRequest {
    @JsonProperty("card_type")
    private Ticket.enumTypesOfCards cardType;
    private String comment;

    public NewTicketRequest() {
        // Nothing here.
    }

    public NewTicketRequest(final Ticket.enumTypesOfCards cardType,
                            final String comment) {
        this.cardType = cardType;
        this.comment = comment;
    }

    public Ticket.enumTypesOfCards getCardType() {
        return cardType;
    }

    public String getComment() {
        return comment;
    }
}
