package com.roger.ppmtool.services;

import com.roger.ppmtool.domain.Project;
import com.roger.ppmtool.exceptions.ProjectIdException;
import com.roger.ppmtool.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project saveOrUpdateProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);

        } catch (Exception e) {
            throw new ProjectIdException(new StringBuilder()
                .append("Project ID: '")
                .append(project.getProjectIdentifier().toUpperCase())
                .append("' already exists").toString());
        }
    }

    public Project findProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null){
            throw new ProjectIdException(new StringBuilder().append("Project ID '").append(projectId.toUpperCase()).append("' does not exists.").toString());
        }

        return project;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if (project == null){
            throw new ProjectIdException(new StringBuilder().append("Cannot delete Project with ID '").append(projectId.toUpperCase()).append("' This project does not exists.").toString());
        }
        projectRepository.delete(project);
    }

}
