/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.service;

import com.wings.designs.ProyectoFraude.persistence.model.Client;
import com.wings.designs.ProyectoFraude.persistence.model.Manager;
import com.wings.designs.ProyectoFraude.persistence.model.User;
import com.wings.designs.ProyectoFraude.persistence.repository.ManagerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManagerService {
    private final ManagerRepository managerRepository;


    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public Optional<Manager> findManagerByEmail(String email) {
        return this.managerRepository.findManagerByEmail(email);
    }

    public Manager getManagerByUser(User user) {
        return managerRepository.getManagerByUser(user);
    }

    public Manager addNewManager(Manager manager) {
        return this.managerRepository.save(manager);
    }
}
