package com.wings.designs.ProyectoFraude.users;


import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {

    @Query("SELECT u FROM Users u WHERE u.id=?1")
    Optional<Users> findUsersById(Long id);
    @Query("SELECT u FROM Users u WHERE u.rut=?1")
    Optional<Users> findUsersByRutUser(String rutUser);
    @Query("SELECT u FROM Users u WHERE u.email=?1")
    Optional<Users> findUsersByEmailUser(String emailUser);

}
