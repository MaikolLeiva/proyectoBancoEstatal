/*
 * Copyright (c) 2021. Wings Design.
 */
package com.wings.designs.ProyectoFraude.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/**
 * Serve as the layer that manages the routes involving the entity for the class
 * {@link com.wings.designs.ProyectoFraude.users.Users Users} .
 * @author Nicolas Henriquez
 * @author Sebastian Zapata
 * @author Ignacio Cabrera
 * @author Maikol Leiva
 * @version 1.0
 */
@RestController
@RequestMapping("api/v1/user")
public class UsersController {
    public final UsersService usersService;
    /**
     * Main constructor of the class.
     * @param usersService An object of the class
     * {@link com.wings.designs.ProyectoFraude.users.UsersService UserService} which contains methods needed to
     * implement the requests.
     */
    @Autowired
    public UsersController (UsersService usersService){
        this.usersService=usersService;
    }
    /**
     * Give all the users that are stored in the database of the system as list of instances of
     * {@link com.wings.designs.ProyectoFraude.users.Users Users}. The number of instances is equal to the number of
     * users that the database of the system has.
     * @return a list of {@link com.wings.designs.ProyectoFraude.users.Users Users}. If there's no users, returns an
     * empty list.
     */
    @GetMapping
    public List<Users> getUsers(){
        return usersService.getUsers();
    }
    /**
     * Receives a instance of an {@link com.wings.designs.ProyectoFraude.users.Users Users} using the annotation
     * <Code>&#64;RequestBody</Code>. Then proceeds to insert it in the system database.
     * @param user An instance of the class {@link com.wings.designs.ProyectoFraude.users.Users Users}. This object will
     * come in a JSON content type.
     */
    @PostMapping("/create")
    public void registerNewUser(@RequestBody Users user){
        usersService.addNewUser(user);
    }
}
