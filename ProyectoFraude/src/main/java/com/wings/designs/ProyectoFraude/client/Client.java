/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wings.designs.ProyectoFraude.ticket.Ticket;
import com.wings.designs.ProyectoFraude.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "client")
public class Client {
    @Id
    @SequenceGenerator(name = "client_sequence", sequenceName = "client_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_sequence")
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "rut", updatable = false, nullable = false)
    private String rut;
    @Column(name = "fullname", nullable = false)
    @JsonProperty("fullname")
    private String fullName;
    @Column(name = "address", nullable = false, columnDefinition = "TEXT")
    private String address;
    @Column(name = "email", updatable = false, unique = true)
    private String email;
    @Column(name = "account_number", unique = true, updatable = false)
    private Long account;
    @Column(name = "phone_number", updatable = false)
    @JsonProperty("phone_number")
    private Long phoneNumber;
    @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    private User user;
    @OneToMany(mappedBy = "client")
    private List<Ticket> ticketList;

    public Client() {
    }

    public Client(String rut, String fullName, String address, String email, Long account, Long phoneNumber,
                  User user) {
        this.rut = rut;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.account = account;
        this.phoneNumber = phoneNumber;
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

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public Long getAccount() {
        return account;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }
    public void addNewTicket(Ticket ticket){
        this.ticketList.add(ticket);
    }
}
