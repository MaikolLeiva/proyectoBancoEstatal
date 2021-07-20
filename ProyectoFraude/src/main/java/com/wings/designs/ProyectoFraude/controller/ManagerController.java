/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.controller;

import com.wings.designs.ProyectoFraude.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The rest Controller in charge of the manager
 * resource's endpoints on the API.
 * @author Nicolas Henriquez
 * @author Sebastian Zapata
 * @author Ignacio Cabrera
 * @author Maikol Leiva
 * @version 1.0
 */
@RestController
@RequestMapping("/managers")
public class ManagerController {

    private final ManagerService managerService;

    @Autowired
    public ManagerController(final ManagerService managerService) {
        this.managerService = managerService;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/tickets/export")
    public void exportManagerReport(@PathVariable Long id,
                                    HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename= report.pdf" ;
        response.setHeader(headerKey, headerValue);
        managerService.exportPdf(response, id);

    }

}
