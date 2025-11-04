package com.example.party;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class ActualEmailSender {

    private final Session session;
    private final String username;


    public ActualEmailSender(String user,String password) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        this.username = user;
        this.session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, password);
                    }
                });
    }

    protected void sendSingleEmail(Invitation invitation, Contact c) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(c.getEmail())
            );
            message.setSubject(invitation.getSubject());
            message.setText(invitation.getMessage());
            Transport.send(message);
            System.out.println("Invitation sent to " + c.getEmail());
        } catch (MessagingException e) {
            System.out.println("Failed to send to " + c.getEmail() + ": " + e.getMessage());
        }
    }
}
