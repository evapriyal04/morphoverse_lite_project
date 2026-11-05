package com.morphoverse.morphoverse.lite.dto;

import java.time.LocalDate;

import com.morphoverse.morphoverse.lite.entity.ProjectState;

public class ProjectResponse {

    private Long id;
    private String name;
    private String description;
    private String clientName;
    private LocalDate targetEndDate;
    private ProjectState state;
    private String pauseReason;

    public ProjectResponse() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public LocalDate getTargetEndDate() { return targetEndDate; }
    public void setTargetEndDate(LocalDate targetEndDate) { this.targetEndDate = targetEndDate; }

    public ProjectState getState() { return state; }
    public void setState(ProjectState state) { this.state = state; }

    public String getPauseReason() { return pauseReason; }
    public void setPauseReason(String pauseReason) { this.pauseReason = pauseReason; }
}