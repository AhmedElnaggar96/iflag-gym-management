package com.iflag.entity;

import com.iflag.enums.AttendanceStatus;
import com.iflag.enums.SessionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name="attendance")
public class Attendance {

    // fields

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Member is required")
    @ManyToOne
    @JoinColumn(name="member_id",nullable = false)
    private MemberProfile member;

    @ManyToOne
    @JoinColumn(name="coach_id")
    private CoachProfile coach;

    @NotNull(message = "Session date is required")
    @Column(name="session_date",nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate sessionDate;

    @NotNull(message = "Session type is required")
    @Enumerated(EnumType.STRING)
    @Column(name="session_type",nullable = false)
    private SessionType sessionType;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    @Column(name="status",nullable = false)
    private AttendanceStatus status;

    private String notes;

    // constructors

    public Attendance() {}

    public Attendance(Long id, MemberProfile member, CoachProfile coach, LocalDate sessionDate, SessionType sessionType, AttendanceStatus status, String notes) {
        this.id = id;
        this.member = member;
        this.coach = coach;
        this.sessionDate = sessionDate;
        this.sessionType = sessionType;
        this.status = status;
        this.notes = notes;
    }

    // getter & setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MemberProfile getMember() {
        return member;
    }

    public void setMember(MemberProfile member) {
        this.member = member;
    }

    public CoachProfile getCoach() {
        return coach;
    }

    public void setCoach(CoachProfile coach) {
        this.coach = coach;
    }

    public LocalDate getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public void setSessionType(SessionType sessionType) {
        this.sessionType = sessionType;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
