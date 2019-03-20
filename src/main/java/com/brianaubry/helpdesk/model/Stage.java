package com.brianaubry.helpdesk.model;

public enum Stage {

    OPEN ("Open"),
    ASSIGNED ("Assigned"),
    WORKING ("Working"),
    HOLD ("On hold"),
    PENDING_INPUT ("Pending input"),
    CLOSED ("Closed");

    private final String name;

    Stage(String name) {this.name = name;}

    public String getName() {return name;}

}
