package com.morphoverse.morphoverse.lite.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

public class ProjectRequest {

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @NotBlank(message = "Client name is required")
    private String clientName;

    @Future(message = "Target end date must be in the future")
    private LocalDate targetEndDate;

    public ProjectRequest() {
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public LocalDate getTargetEndDate() { return targetEndDate; }
    public void setTargetEndDate(LocalDate targetEndDate) { this.targetEndDate = targetEndDate; }
}