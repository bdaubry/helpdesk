package com.brianaubry.helpdesk.models;

import com.brianaubry.helpdesk.models.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String name;

    @NotNull
    private Role role;

    @NotNull
    @Size(min=3, max=12)
    private String username;

    @NotNull
    @Size(min=8)
    private String password;

    @OneToMany
    private List<Ticket> createdTickets = new ArrayList<>();

    @OneToMany
    private List<Ticket> assignedTickets = new ArrayList<>();

    public User() {

    }

    public User(String name, Role role, String username) {
        this.name = name;
        this.role = role;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Ticket> getCreatedTickets() {
        return createdTickets;
    }

    public List<Ticket> getAssignedTickets() {
        return assignedTickets;
    }
}
