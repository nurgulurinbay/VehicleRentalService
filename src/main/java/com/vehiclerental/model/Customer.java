package com.vehiclerental.model;

public class Customer {
    private final String id;
    private final String name;
    private final String email;

    public Customer(String id, String name, String email) {
        if (id == null || id.isBlank()
                || name == null || name.isBlank()
                || email == null || email.isBlank()) {
            throw new IllegalArgumentException("Customer fields cannot be empty.");
        }
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

