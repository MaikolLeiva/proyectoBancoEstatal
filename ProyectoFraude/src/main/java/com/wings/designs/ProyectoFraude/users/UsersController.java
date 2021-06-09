package com.wings.designs.ProyectoFraude.users;

import com.wings.designs.ProyectoFraude.client.Client;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/user")
public class UsersController {
    public final UsersService usersService;

    @Autowired
    public UsersController (UsersService usersService){
        this.usersService=usersService;

    }


    @GetMapping
    public List<Users> getUsers(){
        return usersService.getUsers();

    }


    @PostMapping("/create")
    public void registerNewUser(@RequestBody Users user){
        user.setUserType("CLIENTE");
        usersService.addNewUser(user);
    }


}
