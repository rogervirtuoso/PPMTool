package com.roger.ppmtool.services;

import com.roger.ppmtool.domain.Backlog;
import com.roger.ppmtool.domain.Project;
import com.roger.ppmtool.exceptions.ProjectIdException;
import com.roger.ppmtool.repositories.BacklogRepository;
import com.roger.ppmtool.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private BacklogRepository backlogRepository;

    public ProjectService(ProjectRepository projectRepository, BacklogRepository backlogRepository) {
        this.projectRepository = projectRepository;
        this.backlogRepository = backlogRepository;
    }

    public Project saveOrUpdateProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

            if (project.getId() == null) {
                Backlog backlog = new Backlog();
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

                project.setBacklog(backlog);
            } else {
                project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
            }

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
        if (project == null) {
            throw new ProjectIdException(new StringBuilder().append("Project ID '").append(projectId.toUpperCase()).append("' does not exists.").toString());
        }

        return project;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if (project == null) {
            throw new ProjectIdException(new StringBuilder().append("Cannot delete Project with ID '").append(projectId.toUpperCase()).append("' This project does not exists.").toString());
        }
        projectRepository.delete(project);
    }

}
