package com.example.party;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailService {
    private final String username;
    private final String password;
    private final Session session;

    public EmailService(String username, String password) {
        this.username = username;
        this.password = password;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        this.session = Session.getInstance(props,
            new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
    }

    public void sendInvitation(Invitation invitation) {
        for (Contact c : invitation.getRecipients()) {
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
}
