package com.cinema.repo.model;

import javax.validation.constraints.NotNull;
import javax.persistence.*;

@Entity
@Table(name = "complaints")
public final class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //private long userId;

    @Column(columnDefinition = "ENUM('OPENED', 'CLOSED')")
    @Enumerated(EnumType.STRING)
    private ComplaintStatus status;

    @NotNull
    private String complaintTheme;

    @NotNull
    private String complaintText;

    public Complaint() {
    }

    public Complaint(String complaintTheme, String complaintText, ComplaintStatus status) {
        this.status = status;
        this.complaintTheme = complaintTheme;
        this.complaintText = complaintText;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /*public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }*/

    public ComplaintStatus getStatus() {
        return status;
    }

    public void setStatus(ComplaintStatus status) {
        this.status = status;
    }

    public String getComplaintTheme() {
        return complaintTheme;
    }

    public void setComplaintTheme(String complaintTheme) {
        this.complaintTheme = complaintTheme;
    }

    public String getComplaintText() {
        return complaintText;
    }

    public void setComplaintText(String complaintText) {
        this.complaintText = complaintText;
    }
}
