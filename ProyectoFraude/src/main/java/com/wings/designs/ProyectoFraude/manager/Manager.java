/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wings.designs.ProyectoFraude.ticket.Ticket;
import com.wings.designs.ProyectoFraude.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "manager")
public class Manager {
    @Id
    @SequenceGenerator(name = "client-sequence", sequenceName = "client_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_sequence")
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "rut", updatable = false, nullable = false)
    private String rut;
    @Column(name = "fullname", nullable = false)
    @JsonProperty("fullname")
    private String fullName;
    @Column(name = "email", updatable = false, unique = true)
    private String email;
    @Column(name = "address", nullable = false, columnDefinition = "TEXT")
    private String address;
    @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    private User user;
    @OneToMany(mappedBy = "manager")
    private List<Ticket> ticketList;

    public Manager() {
    }

    public Manager(String rut, String fullName, String email, String address, User user) {
        this.rut = rut;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.user = user;
        this.ticketList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getRut() {
        return rut;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public User getUser() {
        return user;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void addNewTicket(Ticket ticket) {
        this.ticketList.add(ticket);
    }
}
