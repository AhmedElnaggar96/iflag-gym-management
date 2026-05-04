package com.iflag.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="clash_events")
public class ClashEvent {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name="clash_date", nullable = false)
    private LocalDate clashDate;

    private String notes;

    // constructors


    public ClashEvent() {}

    public ClashEvent(Long id, String title, LocalDate clashDate, String notes) {
        this.id = id;
        this.title = title;
        this.clashDate = clashDate;
        this.notes = notes;
    }

    // getter & setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getClashDate() {
        return clashDate;
    }

    public void setClashDate(LocalDate clashDate) {
        this.clashDate = clashDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
