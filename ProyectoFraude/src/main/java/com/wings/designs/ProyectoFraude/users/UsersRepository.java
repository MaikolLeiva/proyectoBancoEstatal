/*
 * Copyright (c) 2021. Wings Design.
 */
package com.wings.designs.ProyectoFraude.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
/**
 *Layer that manages the requests made for the database that are related to the entity Users.
 */
@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    /**
     * Search for an user in the system database by it's Id, if that user exists returns that user.
     * @param id Id of the {@link com.wings.designs.ProyectoFraude.users.Users User} that is wanted.
     * @return {@link com.wings.designs.ProyectoFraude.users.Users User} with the Id specified or null if the user does
     * not exists.
     */
    @Query("SELECT u FROM Users u WHERE u.id=?1")
    Optional<Users> findUsersById(Long id);
    /**
     * Search for an user in the system database by his Rut, if that user exists returns that user.
     * @param rutUser Rut of the {@link com.wings.designs.ProyectoFraude.users.Users User} that is wanted.
     * @return {@link com.wings.designs.ProyectoFraude.users.Users User} with the Rut specified or null if the user does
     * not exists.
     */
    @Query("SELECT u FROM Users u WHERE u.rut=?1")
    Optional<Users> findUsersByRutUser(String rutUser);
    /**
     * Search for an user in the system database by his email, if that user exists returns that user.
     * @param emailUser email of the {@link com.wings.designs.ProyectoFraude.users.Users User} that is looked for.
     * @return {@link com.wings.designs.ProyectoFraude.users.Users User} with the email specified or null if the user
     * does not exists.
     */
    @Query("SELECT u FROM Users u WHERE u.email=?1")
    Optional<Users> findUsersByEmailUser(String emailUser);

}
