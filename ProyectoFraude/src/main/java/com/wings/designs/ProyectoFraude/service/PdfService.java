/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.wings.designs.ProyectoFraude.persistence.model.Ticket;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@Service
public class PdfService {

    public PdfService() {
    }

    public void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.ORANGE.darker());
        cell.setPadding(5);
        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.COURIER);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("TICKET ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("TYPE OF CARD", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("CLIENT RUT", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("CLIENT NAME", font));
        table.addCell(cell);

    }

    public void writeTabledata(PdfPTable table,
                               List<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            if (ticket.getStatus().equals(Ticket.enumStatesOfTicket.CLOSED)) {
                table.addCell(String.valueOf(ticket.getId()));
                table.addCell(ticket.getCardType().name());
                table.addCell(ticket.getClient().getRut());
                table.addCell(ticket.getClient().getFullName());
            }
        }
    }

    public void getManagerReport(List<Ticket> tickets,
                                 String name,
                                 HttpServletResponse response)
            throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        document.add(new Paragraph
                ("REPORT OF CLOSED TICKETS OF "
                        + name.toUpperCase()));
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setSpacingBefore(20);
        writeTableHeader(table);
        writeTabledata(table, tickets);
        document.add(table);
        document.close();
    }
}
