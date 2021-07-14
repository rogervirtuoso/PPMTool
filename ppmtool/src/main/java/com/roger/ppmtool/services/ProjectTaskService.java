package com.roger.ppmtool.services;

import com.roger.ppmtool.domain.Backlog;
import com.roger.ppmtool.domain.ProjectNotFoundExceptionResponse;
import com.roger.ppmtool.domain.ProjectTask;
import com.roger.ppmtool.exceptions.ProjectNotFoundException;
import com.roger.ppmtool.repositories.BacklogRepository;
import com.roger.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProjectTaskService {

    private BacklogRepository backlogRepository;
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTaskService(BacklogRepository backlogRepository, ProjectTaskRepository projectTaskRepository) {
        this.backlogRepository = backlogRepository;
        this.projectTaskRepository = projectTaskRepository;
    }

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {

        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());

        if (backlog == null) {
            throw new ProjectNotFoundException(new ProjectNotFoundExceptionResponse(new StringBuilder().append("Project ").append(projectIdentifier).append(" Not found.").toString()));
        }

        projectTask.setBacklog(backlog);

        Integer ptSequence = backlog.getPTSequence();
        ptSequence++;
        backlog.setPTSequence(ptSequence);

        projectTask.setProjectSequence(
            new StringBuilder()
                .append(backlog.getProjectIdentifier().toUpperCase())
                .append(" - ").append(ptSequence).toString());
        projectTask.setProjectIdentifier(projectIdentifier);

        if (projectTask.getPriority() == null || projectTask.getPriority() == 0) {
            projectTask.setPriority(3);
        }
        if (StringUtils.isEmpty(projectTask.getStatus())) {
            projectTask.setStatus("TO_DO");
        }

        return projectTaskRepository.save(projectTask);

    }

    public Iterable<ProjectTask> findBacklogById(String id) {
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }
}
