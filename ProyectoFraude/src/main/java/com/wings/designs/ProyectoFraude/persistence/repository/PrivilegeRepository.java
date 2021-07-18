/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.persistence.repository;

import com.wings.designs.ProyectoFraude.persistence.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    /**
     * Obtain the Privilege that matches the privilege name given.
     *
     * @param name Is the privilege name of the raw wanted.
     * @return Null if theres's no privilege with that name, if not
     * returns an instance of{@link Privilege} that represents the raw found.
     */
    Privilege findByName(Privilege.EnumPrivilege name);
}
