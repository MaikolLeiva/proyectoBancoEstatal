package com.wings.designs.ProyectoFraude.users;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UsersService(UsersRepository usersRepository) {
        this.usersRepository=usersRepository;

    }
    public List<Users> getUsers(){
        return usersRepository.findAll();


    }

    public void addNewUser(Users user) {
        Optional<Users> usersOptional =
                usersRepository.findUsersByRutUser(user.getRut());
        Optional<Users> usersOptional2 =
                usersRepository.findUsersByEmailUser(user.getEmail());
        if (usersOptional.isPresent()){
            throw new IllegalStateException("Rut tomado");
        }
        if (usersOptional2.isPresent()){
            throw new IllegalStateException("Correo tomado");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(user);

    }

}
