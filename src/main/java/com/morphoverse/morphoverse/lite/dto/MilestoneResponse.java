package com.morphoverse.morphoverse.lite.dto;

import java.time.LocalDate;

import com.morphoverse.morphoverse.lite.entity.MilestoneState;

public class MilestoneResponse {

    private Long id;
    private Long projectId;
    private String name;
    private LocalDate baselineDate;
    private LocalDate currentDate;
    private MilestoneState state;

    public MilestoneResponse() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getBaselineDate() { return baselineDate; }
    public void setBaselineDate(LocalDate baselineDate) { this.baselineDate = baselineDate; }

    public LocalDate getCurrentDate() { return currentDate; }
    public void setCurrentDate(LocalDate currentDate) { this.currentDate = currentDate; }

    public MilestoneState getState() { return state; }
    public void setState(MilestoneState state) { this.state = state; }
}