package com.brianaubry.helpdesk.model;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "status")
public class Status implements Comparable<Status>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="status_id")
    private int id;

    @NotNull
    private String description;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    private String author;

    public Status() {

    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public int compareTo(Status status){
        if (getUpdateDate() == null || status.getUpdateDate() == null){
            return 0;
        }
        return getUpdateDate().compareTo(status.getUpdateDate());
    }

}
