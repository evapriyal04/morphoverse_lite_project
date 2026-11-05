package com.morphoverse.morphoverse.lite.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.morphoverse.morphoverse.lite.dto.ProjectRequest;
import com.morphoverse.morphoverse.lite.dto.ProjectResponse;
import com.morphoverse.morphoverse.lite.dto.StateChangeRequest;
import com.morphoverse.morphoverse.lite.entity.Project;
import com.morphoverse.morphoverse.lite.entity.ProjectState;
import com.morphoverse.morphoverse.lite.exception.InvalidStateTransitionException;
import com.morphoverse.morphoverse.lite.exception.ResourceNotFoundException;
import com.morphoverse.morphoverse.lite.repository.ProjectRepository;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public ProjectResponse create(ProjectRequest request) {
        Project project = new Project();
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setClientName(request.getClientName());
        project.setTargetEndDate(request.getTargetEndDate());
        project.setState(ProjectState.SETUP);

        Project saved = projectRepository.save(project);
        return toResponse(saved);
    }

    public List<ProjectResponse> getAll() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectResponse> responses = new ArrayList<>();

        for (Project project : projects) {
            responses.add(toResponse(project));
        }
        return responses;
    }

    public ProjectResponse getById(Long id) {
        Project project = findProject(id);
        return toResponse(project);
    }

    public ProjectResponse update(Long id, ProjectRequest request) {
        Project existing = findProject(id);
        existing.setName(request.getName());
        existing.setDescription(request.getDescription());
        existing.setClientName(request.getClientName());
        existing.setTargetEndDate(request.getTargetEndDate());

        Project saved = projectRepository.save(existing);
        return toResponse(saved);
    }

    public void delete(Long id) {
        Project existing = findProject(id);
        projectRepository.delete(existing);
    }

    public ProjectResponse changeState(Long id, StateChangeRequest request) {
        Project project = findProject(id);

        if (!isAllowed(project.getState(), request.getNewState())) {
            throw new InvalidStateTransitionException(
                    "Cannot change project from " + project.getState() + " to " + request.getNewState());
        }

        project.setState(request.getNewState());
        Project saved = projectRepository.save(project);
        return toResponse(saved);
    }

    private boolean isAllowed(ProjectState from, ProjectState to) {
        if (from == ProjectState.SETUP && to == ProjectState.ACTIVE) return true;
        if (from == ProjectState.ACTIVE && to == ProjectState.PAUSED) return true;
        if (from == ProjectState.ACTIVE && to == ProjectState.CLOSED) return true;
        if (from == ProjectState.PAUSED && to == ProjectState.ACTIVE) return true;
        return false;
    }

    private Project findProject(Long id) {
        Optional<Project> optional = projectRepository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new ResourceNotFoundException("Project not found with id " + id);
        }
    }

    private ProjectResponse toResponse(Project project) {
        ProjectResponse response = new ProjectResponse();
        response.setId(project.getId());
        response.setName(project.getName());
        response.setDescription(project.getDescription());
        response.setClientName(project.getClientName());
        response.setTargetEndDate(project.getTargetEndDate());
        response.setState(project.getState());
        return response;
    }
}