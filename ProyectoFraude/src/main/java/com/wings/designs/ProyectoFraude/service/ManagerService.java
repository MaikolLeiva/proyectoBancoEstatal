/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.service;

import com.wings.designs.ProyectoFraude.persistence.model.Manager;
import com.wings.designs.ProyectoFraude.persistence.model.Ticket;
import com.wings.designs.ProyectoFraude.persistence.model.User;
import com.wings.designs.ProyectoFraude.persistence.repository.ManagerRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final PdfService pdfService;


    public ManagerService(final ManagerRepository managerRepository,
                          PdfService pdfService) {
        this.managerRepository = managerRepository;
        this.pdfService = pdfService;
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

    public void exportPdf(HttpServletResponse response, Long managerId) throws IOException {
        Manager manager = managerRepository.getManagerById(managerId);
        List<Ticket> list = manager.getTicketList();
        this.pdfService.getManagerReport(list, manager.getFullName(), response);


    }
}
