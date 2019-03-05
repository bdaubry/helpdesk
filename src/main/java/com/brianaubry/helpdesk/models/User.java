package com.brianaubry.helpdesk.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String name;

    @ManyToMany
    private Set<Role> roles;

    @NotNull
    @Size(min=3, max=12)
    private String username;

    @NotNull
    @Size(min=8)
    private String password;

    @NotNull
    @Transient
    private String passwordConfirm;

    @OneToMany
    private List<Ticket> createdTickets = new ArrayList<>();

    @OneToMany
    private List<Ticket> assignedTickets = new ArrayList<>();

    public User() {

    }

    public User(String name, Role role, String username) {
        this.name = name;
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

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public List<Ticket> getCreatedTickets() {
        return createdTickets;
    }

    public List<Ticket> getAssignedTickets() {
        return assignedTickets;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
