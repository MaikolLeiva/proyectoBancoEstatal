/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.spring;

import com.wings.designs.ProyectoFraude.persistence.model.Manager;
import com.wings.designs.ProyectoFraude.persistence.model.User;
import com.wings.designs.ProyectoFraude.persistence.model.Role;
import com.wings.designs.ProyectoFraude.persistence.repository.RoleRepository;
import com.wings.designs.ProyectoFraude.service.ManagerService;
import com.wings.designs.ProyectoFraude.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.*;

@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadySetup = false;
    @Autowired
    private UserService userService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    @TransactionalEventListener
    public void onApplicationEvent(final ContextRefreshedEvent event) {

        if (alreadySetup) {
            return;
        }
        createRole(Role.enumRole.ROLE_MANAGER);
        createRole(Role.enumRole.ROLE_CLIENT);
        Role managerRole = roleRepository.findByName(Role.enumRole.ROLE_MANAGER);
        createManagerUsers("10744718-0", "correofalso@gmail.com",
                "Marco Polo", "Calle falsa 123",
                managerRole, "1234");
        createManagerUsers("13933875-8", "correo123@gmail.com",
                "Juan Carlos", "Calle falsa 2234",
                managerRole, "1234");
        alreadySetup = true;

    }

    /**
     * Receives the name of the Role, and if the name is
     * not found in the Role table, then add a Role to the table with that
     * name and privileges altogether.
     * @param name it's the name of the Role.
     * @return A role object that represents the role with his privileges
     * if the role was added successfully, otherwise returns a null.
     */
    @Transactional
    Role createRole(final Role.enumRole name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
        }
        role = roleRepository.save(role);
        return role;
    }

    @Transactional
    void createManagerUsers(final String rut, final String email,
                            final String fullname, final String address,
                            final Role role, final String password) {
        Optional<User> user = userService.findUserByRut(rut);
        Optional<Manager> manager = managerService.findManagerByEmail(email);
        if (!user.isPresent() && !manager.isPresent()) {
            User newUser = new User(rut, passwordEncoder.encode(password), role);
            Manager newManager = new Manager(rut, fullname, email,
                    address, newUser);
            this.managerService.addNewManager(newManager);
        }
    }
}
