package com.iflag.entity;

import com.iflag.enums.SessionType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="coach_id", nullable = false)
    private CoachProfile coach;

    @Column(nullable = false)
    private String title;

    @Column(name="session_date", nullable = false)
    private LocalDate sessionDate;

    @Column(name="start_time", nullable = false)
    private LocalTime startTime;

    @Column(name="end_time", nullable = false)
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(name="session_type", nullable = false)
    private SessionType sessionType;

    private String notes;

    // constructors


    public Schedule() {}

    public Schedule(Long id, CoachProfile coach, String title, LocalDate sessionDate, LocalTime startTime, LocalTime endTime, SessionType sessionType, String notes) {
        this.id = id;
        this.coach = coach;
        this.title = title;
        this.sessionDate = sessionDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sessionType = sessionType;
        this.notes = notes;
    }

    // getter & setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CoachProfile getCoach() {
        return coach;
    }

    public void setCoach(CoachProfile coach) {
        this.coach = coach;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public void setSessionType(SessionType sessionType) {
        this.sessionType = sessionType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
