/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.spring;

import com.wings.designs.ProyectoFraude.persistence.model.Manager;
import com.wings.designs.ProyectoFraude.persistence.model.User;
import com.wings.designs.ProyectoFraude.persistence.model.Role;
import com.wings.designs.ProyectoFraude.service.ManagerService;
import com.wings.designs.ProyectoFraude.service.RoleService;
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
    /**
     * Flag to check if the method to
     * set things at the start was executed already.
     */
    private boolean alreadySetup = false;

    /**
     * Service to retrieve and send information
     * to the user table in the database.
     */
    @Autowired
    private UserService userService;

    /**
     * Service to retrieve and send information
     * to the manager table in the database.
     */
    @Autowired
    private ManagerService managerService;

    /**
     * Class that though his methods allow
     * to make request or retrieve data to
     * the role table in the database.
     */
    @Autowired
    private RoleService roleService;

    /**
     * Encoder of passwords.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * Set the database with the roles of the users,
     * also insert all the managers in the database.
     * @param event the event to respond to.
     */
    @Override
    @TransactionalEventListener
    public void onApplicationEvent(final ContextRefreshedEvent event) {

        if (alreadySetup) {
            return;
        }
        createRole(Role.enumRole.ROLE_MANAGER);
        createRole(Role.enumRole.ROLE_CLIENT);
        Role managerRole = roleService.findRoleByName(
                Role.enumRole.ROLE_MANAGER);
        createManagerUsers("12803536-2","jacobquesada@bancoestatal.cl",
                "Jacob Quesada",
                "Cerro Negro 01656",
                managerRole,
                "9158");
        createManagerUsers("7056270-7",
                "manueljesus@bancoestatal.cl",
                "Manuel Jesus Torregrosa",
                "Eduardo Orchard 499",
                managerRole,
                "3280");
        createManagerUsers("6147378-5",
                "inmafuentes@bancoestatal.cl",
                "Inma Fuentes",
                "José Santos Ossa 2332,",
                managerRole,
                "8180");
        createManagerUsers("13486185-1",
                "felicianasuarez@bancoestatal.cl",
                "Feliciana Suarez",
                "Buenos Aires 902",
                managerRole,
                "6200");
        createManagerUsers("19791362-2",
                "amaiabuendia@bancoestatal.cl",
                "Amaia Buendia",
                "Maullín 6097",
                managerRole,
                "1992");
        createManagerUsers("20249408-0",
                "junebarbero@bancoestatal.cl",
                "June Barbero",
                "Av. Huamachuco 8797",
                managerRole,
                "6444");
        createManagerUsers("21762215-8",
                "josefaparada@bancoestatal.cl",
                "Josefa Parada",
                "Río Lontué 435",
                managerRole,
                "5590");
        createManagerUsers("17026840-7",
                "leandrocastello@bancoestatal.cl",
                "Leandro Castello",
                "Jardines del Nte. III 38",
                managerRole,
                "9926");
        createManagerUsers("16051145-1",
                "teodorobustos@bancoestatal.cl",
                "Teodoro Bustos",
                "Vilama 516",
                managerRole,
                "2207");
        createManagerUsers("14163870-k",
                "arseniogimenez@bancoestatal.cl",
                "Arsenio Gimenez",
                "Punta Arenas 5510",
                managerRole,
                "9894");
        createManagerUsers("6084521-2",
                "moussasalazar@bancoestatal.cl",
                "Moussa Salazar",
                "Chiloé 4100",
                managerRole,
                "7266");
        createManagerUsers("16283355-3",
                "danielventura@bancoestatal.cl",
                "Daniel Ventura",
                "Bellavista 3704",
                managerRole,
                "3840");
        createManagerUsers("11891488-0",
                "felicidadrobledo@bancoestatal.cl",
                "Felicidad Robledo",
                "Copiapó 900",
                managerRole,
                "8876");
        createManagerUsers("23406814-8",
                "ernestocamps@bancoestatal.cl",
                "Ernesto Camps",
                "Quillota 580",
                managerRole,
                "0189");
        createManagerUsers("7112732-k",
                "ivetespinosa@bancoestatal.cl",
                "Ivet Espinosa",
                "Gabriela Mistral 261",
                managerRole,
                "0006");

        alreadySetup = true;

    }

    /**
     * Receives the name of the Role, and if the name is
     * not found in the Role table, then add a Role to the table with that
     * name and privileges altogether.
     *
     * @param name it's the name of the Role.
     */
    @Transactional
    void createRole(final Role.enumRole name) {
        Role role = roleService.findRoleByName(name);
        if (role == null) {
            role = new Role(name);
        }
        role = roleService.save(role);
    }

    /**
     * Given all the information needed to create a manager,
     * creates one.
     * @param rut rut of the manager.
     * @param email email of the manager.
     * @param fullname complete name of the manager.
     * @param address address of the manager.
     * @param role role of the manager.
     * @param password password of the manager.
     */
    @Transactional
    void createManagerUsers(final String rut, final String email,
                            final String fullname, final String address,
                            final Role role, final String password) {
        Optional<User> user = userService.findUserByRut(rut);
        Optional<Manager> manager = managerService.findManagerByEmail(email);
        if (!user.isPresent() && !manager.isPresent()) {
            User newUser = new User(rut.toUpperCase(), passwordEncoder.encode(password), role);
            Manager newManager = new Manager(rut.toUpperCase(), fullname, email,
                    address, newUser);
            this.managerService.addNewManager(newManager);
        }
    }
}
