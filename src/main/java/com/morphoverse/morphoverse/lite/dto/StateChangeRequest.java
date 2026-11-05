package com.morphoverse.morphoverse.lite.dto;

import com.morphoverse.morphoverse.lite.entity.ProjectState;

import jakarta.validation.constraints.NotNull;

public class StateChangeRequest {

    @NotNull(message = "New state is required")
    private ProjectState newState;

    public StateChangeRequest() {
    }

    public ProjectState getNewState() { return newState; }
    public void setNewState(ProjectState newState) { this.newState = newState; }
}