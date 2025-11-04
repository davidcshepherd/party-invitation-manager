package com.example.party;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailService {

    private final ActualEmailSender sender;

    public EmailService(ActualEmailSender sender) {
        this.sender = sender;

    }

    public void sendInvitation(Invitation invitation) {
        for (Contact c : invitation.getRecipients()) {
            sender.sendSingleEmail(invitation, c);
        }
    }


}
