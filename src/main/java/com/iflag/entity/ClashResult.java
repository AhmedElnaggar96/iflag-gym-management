package com.iflag.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="clash_results")
public class ClashResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Clash event is required")
    @ManyToOne
    @JoinColumn(name="clash_event_id", nullable=false)
    private ClashEvent clashEvent;

    @NotNull(message = "Member is required")
    @ManyToOne
    @JoinColumn(name="member_id", nullable=false)
    private MemberProfile member;

    @NotNull(message = "Pull Ups is required")
    @Column(name="pull_ups", nullable=false)
    private Integer pullUps;

    @NotNull(message = "Dips is required")
    @Column(nullable=false)
    private Integer dips;

    @NotNull(message = "Push Ups is required")
    @Column(name="push_ups", nullable=false)
    private Integer pushUps;

    @NotNull(message = "Squats is required")
    @Column(nullable=false)
    private Integer squats;

    @NotNull(message = "Sit Ups is required")
    @Column(name="sit_ups", nullable=false)
    private Integer sitUps;

    private String notes;

    public ClashResult() {}

    public ClashResult(Long id, ClashEvent clashEvent, MemberProfile member,
                       Integer pullUps, Integer dips, Integer pushUps,
                       Integer squats, Integer sitUps, String notes) {
        this.id = id;
        this.clashEvent = clashEvent;
        this.member = member;
        this.pullUps = pullUps;
        this.dips = dips;
        this.pushUps = pushUps;
        this.squats = squats;
        this.sitUps = sitUps;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClashEvent getClashEvent() {
        return clashEvent;
    }

    public void setClashEvent(ClashEvent clashEvent) {
        this.clashEvent = clashEvent;
    }

    public MemberProfile getMember() {
        return member;
    }

    public void setMember(MemberProfile member) {
        this.member = member;
    }

    public Integer getPullUps() {
        return pullUps;
    }

    public void setPullUps(Integer pullUps) {
        this.pullUps = pullUps;
    }

    public Integer getDips() {
        return dips;
    }

    public void setDips(Integer dips) {
        this.dips = dips;
    }

    public Integer getPushUps() {
        return pushUps;
    }

    public void setPushUps(Integer pushUps) {
        this.pushUps = pushUps;
    }

    public Integer getSquats() {
        return squats;
    }

    public void setSquats(Integer squats) {
        this.squats = squats;
    }

    public Integer getSitUps() {
        return sitUps;
    }

    public void setSitUps(Integer sitUps) {
        this.sitUps = sitUps;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}