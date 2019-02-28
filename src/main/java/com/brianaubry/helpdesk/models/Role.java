package com.brianaubry.helpdesk.models;

public enum Role {

    ADMIN("Administrator"),
    TECH("Tech"),
    USER("User");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
