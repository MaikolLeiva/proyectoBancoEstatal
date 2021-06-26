/*
 * Copyright (c) 2021. Wings Design.
 */
package com.wings.designs.ProyectoFraude.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
/**
 *Layer that manages the requests made for the database that are related to the entity User.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Search for an user in the system database by it's Id, if that user exists returns that user.
     * @param id Id of the {@link User User} that is wanted.
     * @return {@link User User} with the Id specified or null if the user does
     * not exists.
     */
    @Query("SELECT u FROM user_account u WHERE u.id=?1")
    Optional<User> findUsersById(Long id);
    /**
     * Search for an user in the system database by his Rut, if that user exists returns that user.
     * @param rutUser Rut of the {@link User User} that is wanted.
     * @return {@link User User} with the Rut specified or null if the user does
     * not exists.
     */
    @Query("SELECT u FROM user_account u WHERE u.rut=?1")
    Optional<User> findUsersByRutUser(String rutUser);
    /**
     * Search for an user in the system database by his email, if that user exists returns that user.
     * @param email email of the {@link User User} that is looked for.
     * @return {@link User User} with the email specified or null if the user
     * does not exists.
     */
    @Query("SELECT u FROM user_account u WHERE u.email=?1")
    Optional<User> findUsersByEmail(String email);
    @Query("SELECT u FROM user_account u WHERE u.rut=?1")
    Optional<User> findUsersByRut(String rut);

}
