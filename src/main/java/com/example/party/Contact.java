package com.example.party;

public class Contact {
    private int id;
    private String name;
    private String email;

    public Contact(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        if(name == null || email == null) throw new NullPointerException();
     }

    public Contact(String name, String email) {
        this(-1, name, email);
    }


    public String getName() { return name; }
    public String getEmail() { return email; }

    @Override
    public boolean equals(Object o) {
        if(o.toString().equals(this.toString()))
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return name + " <" + email + ">";
    }
}
