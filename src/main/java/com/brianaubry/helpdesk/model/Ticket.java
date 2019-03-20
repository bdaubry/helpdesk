package com.brianaubry.helpdesk.model;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min=1, max=50, message = "Title too long (max 50 characters)")
    private String title;

    @NotNull
    @Size(min=1, max=500, message = "Description too long (max 500 characters)")
    private String description;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOpened;

    private Date dateClosed;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="ticket_status", joinColumns=@JoinColumn(name="ticket_id"), inverseJoinColumns=@JoinColumn(name="status_id"))
    private List<Status> updates;

    @ManyToOne
    @CreatedBy
    private User createdBy;

    @ManyToOne
    private User assignedTo;

    private Stage stage;

    public Ticket() {
        this.stage = Stage.OPEN;
    }

    public Ticket(String title, String description, User createdBy) {
        this.title = title;
        this.description = description;
        this.createdBy = createdBy;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(Date dateOpened) {
        this.dateOpened = dateOpened;
    }

    public Date getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(Date dateClosed) {
        this.dateClosed = dateClosed;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public List<Status> getUpdates() {
        return updates;
    }

    public void setUpdates(List<Status> updates) {
        this.updates = updates;
    }

    public void addUpdate(Status update){
        updates.add(update);
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
