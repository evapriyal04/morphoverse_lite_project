package com.morphoverse.morphoverse.lite.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.morphoverse.morphoverse.lite.dto.MilestoneRequest;
import com.morphoverse.morphoverse.lite.dto.MilestoneResponse;
import com.morphoverse.morphoverse.lite.service.MilestoneService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/projects/{projectId}/milestones")
public class MilestoneController {

    private final MilestoneService milestoneService;

    public MilestoneController(MilestoneService milestoneService) {
        this.milestoneService = milestoneService;
    }

    @PostMapping
    public ResponseEntity<MilestoneResponse> create(@PathVariable Long projectId,
                                                    @Valid @RequestBody MilestoneRequest request) {
        MilestoneResponse response = milestoneService.create(projectId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public List<MilestoneResponse> getByProject(@PathVariable Long projectId) {
        return milestoneService.getByProject(projectId);
    }
}