/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.user.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Obtain the raw of the table Roles that matches the rol name given. The raw is given as a instance of the class
     * {@link com.wings.designs.ProyectoFraude.user.role.Role Role}.
     * @param name Is the role name of the raw wanted.
     * @return Null if theres's no raw with that role name, if not, returns an instance of
     * {@link com.wings.designs.ProyectoFraude.user.role.Role Role} that represents the raw found.
     */
    Role findByName(Role.enumRole name);
}
