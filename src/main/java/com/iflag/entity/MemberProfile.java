package com.iflag.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name="member_profiles")
public class MemberProfile {

    // Fields

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="user_id", nullable=false, unique=true)
    private User user;

    @NotNull(message = "Total sessions is required")
    @Min(value = 0, message = "Total sessions cannot be negative")
    @Column(name="total_sessions", nullable=false)
    private Integer totalSessions;

    @NotNull(message = "Extra sessions is required")
    @Min(value = 0, message = "Extra sessions cannot be negative")
    @Column(name="extra_sessions", nullable=false)
    private Integer extraSessions;

    @NotNull(message = "Start date is required")
    @Column(name="start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @Column(name="end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private String notes;

    // Constructors
    public MemberProfile() {}

    public MemberProfile(Long id, User user, Integer totalSessions, Integer extraSessions, LocalDate startDate, LocalDate endDate, String notes) {
        this.id = id;
        this.user = user;
        this.totalSessions = totalSessions;
        this.extraSessions = extraSessions;
        this.startDate = startDate;
        this.endDate = endDate;
        this.notes = notes;
    }

    // getter & setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getTotalSessions() {
        return totalSessions;
    }

    public void setTotalSessions(Integer totalSessions) {
        this.totalSessions = totalSessions;
    }

    public Integer getExtraSessions() {
        return extraSessions;
    }

    public void setExtraSessions(Integer extraSessions) {
        this.extraSessions = extraSessions;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
