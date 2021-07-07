/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.spring;

import com.wings.designs.ProyectoFraude.persistence.model.User;
import com.wings.designs.ProyectoFraude.persistence.repository.UserRepository;
import com.wings.designs.ProyectoFraude.persistence.model.Privilege;
import com.wings.designs.ProyectoFraude.persistence.repository.PrivilegeRepository;
import com.wings.designs.ProyectoFraude.persistence.model.Role;
import com.wings.designs.ProyectoFraude.persistence.repository.RoleRepository;
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
    private UserRepository userRepository;
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
        Role clientRole = roleRepository.findByName(Role.enumRole.ROLE_CLIENT);
        createManagerUsers("231231231-8", "1234", managerRole);
        createManagerUsers("31214124-8", "1234", clientRole);
        alreadySetup = true;

    }

    /**
     * Receives the name of the Privilege, and if the name is not found in the Privilege table, then add a Privilege
     * to the database with that name.
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
     * @param name it's the name of the Role
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
    User createManagerUsers(final String rut, final String password, final Role role) {
        Optional<User> user = userRepository.findUsersByRut(rut);
        User newUser = null;
        if (!user.isPresent()) {
            newUser = new User(rut, passwordEncoder.encode(password), role);
            newUser = userRepository.save(newUser);
        }
        return newUser;
    }
}
