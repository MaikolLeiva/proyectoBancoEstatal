/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.persistence.repository;

import com.wings.designs.ProyectoFraude.persistence.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    /**
     * Search for the client with the given id and returns and Optional object
     * to check if the client is present or not.
     * @param id The id of the client wanted
     * @return An optional instance in any case.
     */
    @Query("SELECT c FROM client c WHERE c.id=?1")
    Optional<Client> findClientById(Long id);

    /**
     * Search for the client with the given rut and returns and Optional object
     * to check if the client is present or not.
     * @param rut The id of the client that is wanted.
     * @return An optional instance in any case.
     */
    @Query("SELECT c FROM client c WHERE c.rut=?1")
    Optional<Client> findClientsByRut(String rut);

    /**
     * Search for the client with the given account number and returns
     * and Optional object to check if the client is present or not.
     * @param account It's the account number of the client wanted.
     * @return An optional instance in any case.
     */
    @Query("SELECT c FROM client c WHERE c.account=?1")
    Optional<Client> findClientsByAccount(Long account);

    /**
     * Search for the client with the given email and returns
     * and Optional object to check if the client is present or not.
     * @param email The email of the client wanted.
     * @return An optional instance in any case.
     */
    @Query("SELECT c FROM client c WHERE c.email=?1")
    Optional<Client> findClientsByEmail(String email);


}
