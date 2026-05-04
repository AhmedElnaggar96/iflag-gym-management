package com.iflag.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="coach_profiles")
public class CoachProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Coach user is required")
    @OneToOne
    @JoinColumn(name="user_id", nullable = false, unique = true)
    private User user;

    @NotBlank(message = "Specialty is required")
    private String specialty;

    private String notes;

    public CoachProfile() {}

    public CoachProfile(Long id, User user, String specialty, String notes) {
        this.id = id;
        this.user = user;
        this.specialty = specialty;
        this.notes = notes;
    }

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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}