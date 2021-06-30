/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.client;

import com.wings.designs.ProyectoFraude.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {

    @Query("SELECT c FROM client c WHERE c.id=?1")
    Optional<Client> findClientById(Long id);

    @Query("SELECT c FROM client c WHERE c.rut=?1")
    Optional<Client> findClientsByRut(String rut);

    @Query("SELECT c FROM client c WHERE c.account=?1")
    Optional<Client> findClientsByAccount(Long account);

    @Query("SELECT c FROM client c WHERE c.email=?1")
    Optional<Client> findClientsByEmail(String email);


}
