/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.persistence.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Represents a Role of the system in the Database.
 *
 * @author Nicolas Henriquez
 * @author Sebastian Zapata
 * @author Ignacio Cabrera
 * @author Maikol Leiva
 * @version 1.0
 */
@Entity(name = "role")
public class Role {
    /**
     * It's the identifier of the role on the database.
     */
    @Id
    @SequenceGenerator(name = "role_sequence", sequenceName =
            "role_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "role_sequence")
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * It's the name of the privilege.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private enumRole name;

    /**
     * A collection that represents all the privileges associated with the role.
     */
    @ManyToMany
    @JoinTable(name = "roles_privileges",
            joinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id",
                    referencedColumnName = "id"))
    private Collection<Privilege> privileges;

    /**
     * Empty Constructor.
     */
    public Role() {
        // Nothing here.
    }

    /**
     * Main constructor.
     *
     * @param name is the name of the Role.
     */
    public Role(final enumRole name) {
        this.name = name;
    }

    /**
     * Give all the privileges associated with the role.
     *
     * @return A collection with all the privileges that the role have.
     */
    public Collection<Privilege> getPrivileges() {
        return privileges;
    }

    /**
     * Set the privileges of the role with a given collection of privileges.
     *
     * @param privileges A collection with all the privileges.
     */
    public void setPrivileges(final Collection<Privilege> privileges) {
        this.privileges = privileges;
    }

    /**
     * Returns the name of the role.
     *
     * @return the name of the role.
     */
    public enumRole getName() {
        return name;
    }

    /**
     * Give the name and id of the role.
     * @return A string with the name of the role and the id.
     */
    @Override
    public String toString() {
        return "Role [name=" + name + "]" + "[id=" + id + "]";
    }

    /**
     * Enum that defines all the possibles roles that the user can have.
     */
    public enum enumRole {
        /**
         * The role client that allow users to create tickets.
         */
        ROLE_CLIENT,

        /**
         * The manager role that allow users to take and close tickets.
         */
        ROLE_MANAGER
    }
}
