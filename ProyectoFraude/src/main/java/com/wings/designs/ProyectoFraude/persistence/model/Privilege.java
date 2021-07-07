/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.persistence.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Represents a Privilege of the system in the Database.
 * @author Nicolas Henriquez
 * @author Sebastian Zapata
 * @author Ignacio Cabrera
 * @author Maikol Leiva
 * @version 1.0
 */
@Entity(name = "privilege")
public class Privilege {
    /**
     * It's the identifier of the privilege on the database.
     */
    @Id
    @SequenceGenerator(name = "privilege_sequence",
            sequenceName = "privilege_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "privilege_sequence")
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * It's the name of the privilege.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private EnumPrivilege name;

    /**
     * It's a collection of {@link Role roles} that represents
     * all the roles that have this privilege.
     */
    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

    /**
     * Empty Constructor.
     */
    public Privilege() {
        // Nothing here.
    }
    /**
     * Main constructor.
     * @param name it's the name of the privilege formatted as enum.
     */
    public Privilege(final EnumPrivilege name) {
        this.name = name;
    }

    /**
     * Returns the name of the privilege.
     * @return the name of the privilege.
     */
    public EnumPrivilege getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Privilege [name=" + name + "]" + "[id=" + id + "]";
    }

    /**
     * Enum that defines all the possibles privileges that the API
     * recognize that need a role to be perform.
     */
    public enum EnumPrivilege {
        /**
         * For get request that involves {@link User users}.
         */
        READ_USER,

        /**
         * For get request that involves{@link Ticket tickets}.
         */
        READ_TICKET,

        /**
         * For post request that involves{@link Ticket tickets}.
         */
        WRITE_TICKET
    }


}
