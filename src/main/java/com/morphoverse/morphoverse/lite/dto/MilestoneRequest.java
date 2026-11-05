package com.morphoverse.morphoverse.lite.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MilestoneRequest {

    @NotBlank(message = "Milestone name is required")
    private String name;

    @NotNull(message = "Baseline date is required")
    private LocalDate baselineDate;

    public MilestoneRequest() {
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getBaselineDate() { return baselineDate; }
    public void setBaselineDate(LocalDate baselineDate) { this.baselineDate = baselineDate; }
}