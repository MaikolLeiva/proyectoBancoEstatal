/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.service;

import com.wings.designs.ProyectoFraude.persistence.model.Manager;
import com.wings.designs.ProyectoFraude.persistence.model.User;
import com.wings.designs.ProyectoFraude.persistence.repository.ManagerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManagerService {
    private final ManagerRepository managerRepository;


    public ManagerService(final ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public Optional<Manager> findManagerByEmail(final String email) {
        return this.managerRepository.findManagerByEmail(email);
    }

    public Manager getManagerByUser(final User user) {
        return managerRepository.getManagerByUser(user);
    }

    public Manager addNewManager(final Manager manager) {
        return this.managerRepository.save(manager);
    }

    public Manager getManagerByRut(String rut) {
        return managerRepository.getManagerByRut(rut);
    }
}
