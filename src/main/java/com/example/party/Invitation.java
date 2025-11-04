package com.example.party;

import java.util.ArrayList;
import java.util.List;

public class Invitation {
    private String subject;
    private String message;
    private final List<Contact> recipients = new ArrayList<>();

    public Invitation(String subject, String message) {
        this.subject = subject;
        this.message = message;
    }

    public void addRecipient(Contact c) {
        recipients.add(c);
    }

    public List<Contact> getRecipients() {
        return recipients;
    }

    public String getSubject() { return subject; }
    public String getMessage() { return message; }
}
