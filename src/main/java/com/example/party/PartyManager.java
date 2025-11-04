package com.example.party;

import java.util.List;

public class PartyManager {
    private final ContactDatabase db;
    private final EmailService emailService;

    public PartyManager(ContactDatabase myDb, EmailService myEmailService) {
        db = myDb;
        emailService = myEmailService;
    }

    public void addContact(String name, String email) {
        db.addContact(new Contact(name, email));
    }

    public List<Contact> getContacts() {
        return db.getAllContacts();
    }

    public void sendInvitation(Invitation invite) {
        emailService.sendInvitation(invite);
    }

    public void sendInvitationToAll(Invitation invite) {
        for(Contact contact : db.getAllContacts()) {
            invite.addRecipient(contact);
        }
        emailService.sendInvitation(invite);
    }


}
