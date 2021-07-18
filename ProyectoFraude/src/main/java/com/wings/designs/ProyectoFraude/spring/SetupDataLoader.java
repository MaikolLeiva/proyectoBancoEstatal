/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.spring;

import com.wings.designs.ProyectoFraude.persistence.model.Manager;
import com.wings.designs.ProyectoFraude.persistence.model.User;
import com.wings.designs.ProyectoFraude.persistence.model.Privilege;
import com.wings.designs.ProyectoFraude.persistence.repository.PrivilegeRepository;
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
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadySetup = false;
    @Autowired
    private UserService userService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PrivilegeRepository privilegeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    @TransactionalEventListener
    public void onApplicationEvent(final ContextRefreshedEvent event) {

        if (alreadySetup) {
            return;
        }
        final Privilege readTicketPrivilege = createPrivilege(Privilege.EnumPrivilege.READ_TICKET);
        final Privilege writeTicketPrivilege = createPrivilege(Privilege.EnumPrivilege.WRITE_TICKET);
        final Privilege readUserPrivilege = createPrivilege(Privilege.EnumPrivilege.READ_USER);
        final List<Privilege> clientPrivileges = new ArrayList<>(Arrays.asList(writeTicketPrivilege));
        final List<Privilege> managerPrivileges = new ArrayList<>(Arrays.asList(readTicketPrivilege, readUserPrivilege));
        createRole(Role.enumRole.ROLE_MANAGER, managerPrivileges);
        createRole(Role.enumRole.ROLE_CLIENT, clientPrivileges);
        Role managerRole = roleRepository.findByName(Role.enumRole.ROLE_MANAGER);
        createManagerUsers("12398465-8", "correofalso@gmail.com", "Marco Polo",
                "Calle falsa 123", managerRole, "1234");
        createManagerUsers("1564798-1", "correo123@gmail.com", "Juan Carlos",
                "Calle falsa 2234", managerRole, "1234");
        alreadySetup = true;

    }

    /**
     * Receives the name of the Privilege, and if the name is not found in the Privilege table, then add a Privilege
     * to the database with that name.
     *
     * @param name it's the name of the Privilege
     * @return A {@link Privilege Privilege} object that represent the
     * privilege added, if the privilege wasn't added, then return null.
     */

    @Transactional
    Privilege createPrivilege(final Privilege.EnumPrivilege name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    /**
     * Receives the name of the Role and his privileges, and if the name is not found in the Role table, then add a Role
     * to the table with that name and privileges altogether.
     *
     * @param name       it's the name of the Role
     * @param privileges All the privileges related to the role.
     * @return A role object that represents the role with his privileges if the role was added successfully, otherwise
     * returns a null.
     */
    @Transactional
    Role createRole(final Role.enumRole name, final Collection<Privilege> privileges) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
        }
        role.setPrivileges(privileges);
        role = roleRepository.save(role);
        return role;
    }

    @Transactional
    void createManagerUsers(final String rut, final String email, final String fullname,
                            final String address, final Role role, final String password) {
        Optional<User> user = userService.findUserByRut(rut);
        Optional<Manager> manager = managerService.findManagerByEmail(email);
        if (!user.isPresent() && !manager.isPresent()) {
            User newUser = new User(rut, passwordEncoder.encode(password), role);
            Manager newManager = new Manager(rut, fullname, email, address, newUser);
            this.managerService.addNewManager(newManager);
        }
    }
}
