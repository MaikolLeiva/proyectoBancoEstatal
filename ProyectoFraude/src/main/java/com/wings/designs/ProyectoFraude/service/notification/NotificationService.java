/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.service.notification;

import com.wings.designs.ProyectoFraude.persistence.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final JavaMailSender javaMailSender;
    private final NotificationConfig notificationConfig;

    @Autowired
    public NotificationService(final JavaMailSender javaMailSender, NotificationConfig notificationConfig) {
        this.javaMailSender = javaMailSender;
        this.notificationConfig = notificationConfig;
    }

    public void sendNotificationToClient(Ticket ticket) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(ticket.getClient().getEmail());
        mail.setFrom(notificationConfig.getUsername());
        String subject = "Your ticket has been closed";
        mail.setSubject(subject);
        String text = "Congratulations: " + ticket.getClient().getFullName()
                + "the ticket with the id: "+ ticket.getId()
                + "associated with your"+ ticket.getCardType()
                + "has been closed by the manager: "+ ticket.getManager().getFullName();
        mail.setText(text);
        javaMailSender.send(mail);

    }
}
