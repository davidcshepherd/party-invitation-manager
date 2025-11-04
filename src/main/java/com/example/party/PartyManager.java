package com.example.party;

import java.util.List;

public class PartyManager {
    private final ContactDatabase db;

    public PartyManager() {
        db = new ContactDatabase();
    }

    public void addContact(String name, String email) {
        db.addContact(new Contact(name, email));
    }

    public List<Contact> getContacts() {
        return db.getAllContacts();
    }
}
