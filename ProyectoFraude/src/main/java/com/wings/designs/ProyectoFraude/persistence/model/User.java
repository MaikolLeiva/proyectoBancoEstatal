/*
 * Copyright (c) 2021. Wings Design.
 */
package com.wings.designs.ProyectoFraude.persistence.model;

import javax.persistence.*;

/**
 * Represents a User of the system with his attributes and it's representation in the Database.
 * @author Nicolas Henriquez
 * @author Sebastian Zapata
 * @author Ignacio Cabrera
 * @author Maikol Leiva
 * @version 1.0
 */
@Entity(name = "user_account")
public class User {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "rut", updatable = false, nullable = false)
    private String rut;
    @Column(name = "password", nullable = false)
    private String password;
    @ManyToOne()
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id",
            referencedColumnName = "id"), inverseJoinColumns =
    @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Role role;

    /**
     * An Empty Constructor.
     */
    public User() {
        // Nothing here.
    }

    /**
     * Constructor that receives all the attributes as parameters,
     * except for the id.
     * @param rut         Represent the rut of the person. A unique number assigned
     *                    for every citizen that lives in Chile.
     * @param password    Is the password of  the user, it has to be encrypted.
     * @param role        Is the type of rol that the user have.
     */
    public User(String rut, String password, Role role) {
        this.rut = rut;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getRut() {
        return rut;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}





