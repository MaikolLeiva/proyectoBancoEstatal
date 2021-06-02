package com.wings.designs.ProyectoFraude.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,String> {


    @Query("SELECT c FROM Client c WHERE c.mail=?1")
    Optional<Client> findClientByMail(String mail);
    @Query("SELECT c FROM Client c WHERE c.accountNumber=?1")
    Optional<Client> findClientByAccountNumber(Long accountNumber);
}
