package com.morphoverse.morphoverse.lite.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "milestones")
public class Milestone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private LocalDate baselineDate;

    private LocalDate currentDate;

    @Enumerated(EnumType.STRING)
    private MilestoneState state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    public Milestone() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getBaselineDate() { return baselineDate; }
    public void setBaselineDate(LocalDate baselineDate) { this.baselineDate = baselineDate; }

    public LocalDate getCurrentDate() { return currentDate; }
    public void setCurrentDate(LocalDate currentDate) { this.currentDate = currentDate; }

    public MilestoneState getState() { return state; }
    public void setState(MilestoneState state) { this.state = state; }

    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
}