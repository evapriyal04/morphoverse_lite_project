package com.morphoverse.morphoverse.lite.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.morphoverse.morphoverse.lite.dto.MilestoneRequest;
import com.morphoverse.morphoverse.lite.dto.MilestoneResponse;
import com.morphoverse.morphoverse.lite.entity.Milestone;
import com.morphoverse.morphoverse.lite.entity.MilestoneState;
import com.morphoverse.morphoverse.lite.entity.Project;
import com.morphoverse.morphoverse.lite.exception.ResourceNotFoundException;
import com.morphoverse.morphoverse.lite.repository.MilestoneRepository;
import com.morphoverse.morphoverse.lite.repository.ProjectRepository;

@Service
public class MilestoneService {

    private final MilestoneRepository milestoneRepository;
    private final ProjectRepository projectRepository;

    public MilestoneService(MilestoneRepository milestoneRepository,
                            ProjectRepository projectRepository) {
        this.milestoneRepository = milestoneRepository;
        this.projectRepository = projectRepository;
    }

    public MilestoneResponse create(Long projectId, MilestoneRequest request) {
        Project project = findProject(projectId);

        Milestone milestone = new Milestone();
        milestone.setName(request.getName());
        milestone.setBaselineDate(request.getBaselineDate());
        milestone.setCurrentDate(request.getBaselineDate());   // starts the same as baseline
        milestone.setState(MilestoneState.PLANNED);
        milestone.setProject(project);

        Milestone saved = milestoneRepository.save(milestone);
        return toResponse(saved);
    }

    public List<MilestoneResponse> getByProject(Long projectId) {
        findProject(projectId);   // throws 404 if the project does not exist

        List<Milestone> milestones = milestoneRepository.findByProjectId(projectId);
        List<MilestoneResponse> responses = new ArrayList<>();

        for (Milestone milestone : milestones) {
            responses.add(toResponse(milestone));
        }
        return responses;
    }

    private Project findProject(Long projectId) {
        Optional<Project> optional = projectRepository.findById(projectId);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new ResourceNotFoundException("Project not found with id " + projectId);
        }
    }

    private MilestoneResponse toResponse(Milestone milestone) {
        MilestoneResponse response = new MilestoneResponse();
        response.setId(milestone.getId());
        response.setProjectId(milestone.getProject().getId());
        response.setName(milestone.getName());
        response.setBaselineDate(milestone.getBaselineDate());
        response.setCurrentDate(milestone.getCurrentDate());
        response.setState(milestone.getState());
        return response;
    }
}