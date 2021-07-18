/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.persistence.repository;

import com.wings.designs.ProyectoFraude.persistence.model.Client;
import com.wings.designs.ProyectoFraude.persistence.model.Manager;
import com.wings.designs.ProyectoFraude.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    /**
     * Search for the manager with the given email and returns
     * and Optional object to check if the manager is present or not.
     *
     * @param email The email of the manager wanted.
     * @return An optional instance in any case.
     */
    @Query("SELECT m FROM manager m WHERE m.email=?1")
    Optional<Manager> findManagerByEmail(String email);

    @Query("SELECT m FROM manager m WHERE m.user.id = :#{#user.id}")
    Manager getManagerByUser(@Param("user") User user);
}
