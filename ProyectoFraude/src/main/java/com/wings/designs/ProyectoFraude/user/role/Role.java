/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.user.role;

import com.wings.designs.ProyectoFraude.user.privilege.Privilege;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "role")
public class Role {
    @Id
    @SequenceGenerator(name = "role_sequence", sequenceName = "role_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_sequence")
    @Column(name = "id", updatable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private enumRole name;

    @ManyToMany
    @JoinTable(name = "roles_privileges",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;

    /**
     * Empty Constructor
     */
    public Role() {
    }

    /**
     * Main constructor.
     * @param name is the name of the Role.
     */
    public Role(enumRole name) {
        this.name = name;
    }


    public Collection<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Collection<Privilege> privileges) {
        this.privileges = privileges;
    }

    public enumRole getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Role [name=" + name + "]" + "[id=" + id + "]";
    }


    /**
     * Enum that defines all the possibles roles that the user can have.
     * <code>CLIENT<code/> allow users to create tickets.
     * <code>MANAGER<code/> allow users to take and close tickets.
     */
    public enum enumRole{
        ROLE_CLIENT,
        ROLE_MANAGER
    }
}
