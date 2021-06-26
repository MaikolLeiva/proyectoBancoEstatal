/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.spring;

import com.wings.designs.ProyectoFraude.user.User;
import com.wings.designs.ProyectoFraude.user.UserRepository;
import com.wings.designs.ProyectoFraude.user.privilege.Privilege;
import com.wings.designs.ProyectoFraude.user.privilege.PrivilegeRepository;
import com.wings.designs.ProyectoFraude.user.role.Role;
import com.wings.designs.ProyectoFraude.user.role.RoleRepository;
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
        createManagerUsers("231231231-8", "Sebastian Murkio", "sebamurcio@hotmail.com",
                "Nueva Ventura 2213", "1234", 2L, managerRole);
        createManagerUsers("31214124-8", "Hans Hennings", "hans@hotmail.com",
                "Terris 1123", "1234", 3L, clientRole);
        alreadySetup = true;

    }

    /**
     * Receives the name of the Privilege, and if the name is not found in the Privilege table, then add a Privilege
     * to the database with that name.
     * @param name it's the name of the Privilege
     * @return A {@link com.wings.designs.ProyectoFraude.user.privilege.Privilege Privilege} object that represent the
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
    User createManagerUsers(final String rut, final String fullName, final String email, final String address,
                            final String password, Long phoneNumber, final Role role) {
        Optional<User> user = userRepository.findUsersByRut(rut);
        Optional<User> user2 = userRepository.findUsersByEmail(email);
        User newUser = null;
        if (!user.isPresent() && !user2.isPresent()) {
            newUser = new User(rut, passwordEncoder.encode(password), fullName, address, email,
                    null, phoneNumber, role);
        }
        newUser = userRepository.save(newUser);
        return newUser;
    }
}
