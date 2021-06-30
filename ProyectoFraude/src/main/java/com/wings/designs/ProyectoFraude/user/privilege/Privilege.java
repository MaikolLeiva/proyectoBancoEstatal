/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.user.privilege;

import com.wings.designs.ProyectoFraude.user.User;
import com.wings.designs.ProyectoFraude.user.role.Role;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "privilege")
public class Privilege {
    @Id
    @SequenceGenerator(name = "privilege_sequence", sequenceName = "privilege_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "privilege_sequence")
    @Column(name = "id", updatable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private EnumPrivilege name;
    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

    /**
     * Empty Constructor
     */
    public Privilege() { }
    /**
     * Main constructor.
     * @param name it's the name of the privilege formatted as enum.
     */
    public Privilege(final EnumPrivilege name) {
        this.name = name;
    }

    public EnumPrivilege getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Privilege [name=" + name + "]" + "[id=" + id + "]";
    }



    /**
     * Enum that defines all the possibles privileges that the API recognize that need a role to be perform.
     * <code>READ_USER<code/> For get request that involves {@link User users}.
     * <code>READ_TICKET<code/> For get request that involves
     * {@link com.wings.designs.ProyectoFraude.ticket.Ticket tickets}.
     * <code>WRITE_TICKET<code/> For post request that involves
     * {@link com.wings.designs.ProyectoFraude.ticket.Ticket tickets}.
     */
    public enum EnumPrivilege {
        READ_USER,
        READ_TICKET,
        WRITE_TICKET

    }


}
