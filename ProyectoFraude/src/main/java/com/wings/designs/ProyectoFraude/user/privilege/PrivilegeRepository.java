/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.user.privilege;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    /**
     * Obtain the raw of the table Privilege that matches the privilege name given. The raw is given as a instance of
     * the class{@link com.wings.designs.ProyectoFraude.user.privilege.Privilege Privilege}.
     * @param name Is the privilege name of the raw wanted.
     * @return Null if theres's no raw with that privilege name, if not, returns an instance of
     * {@link com.wings.designs.ProyectoFraude.user.privilege.Privilege} that represents the raw found.
     */
    Privilege findByName(Privilege.EnumPrivilege name);
}
