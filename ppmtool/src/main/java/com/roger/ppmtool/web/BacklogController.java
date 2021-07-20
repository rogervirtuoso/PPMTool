package com.roger.ppmtool.web;

import com.roger.ppmtool.domain.ProjectTask;
import com.roger.ppmtool.services.MapValidationErrorService;
import com.roger.ppmtool.services.ProjectTaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {

    private ProjectTaskService projectTaskService;
    private MapValidationErrorService mapValidationErrorService;

    public BacklogController(ProjectTaskService projectTaskService, MapValidationErrorService mapValidationErrorService) {
        this.projectTaskService = projectTaskService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addPTtoBacklog(@Valid @RequestBody ProjectTask projectTask,
                                            BindingResult result, @PathVariable String backlog_id) {
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if (errorMap != null) return errorMap;

        ProjectTask projectTask1 = projectTaskService.addProjectTask(backlog_id, projectTask);
        return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);
    }

    @GetMapping("/{backlog_id}")
    public Iterable<ProjectTask> getProjectBacklog(@PathVariable String backlog_id) {
        return projectTaskService.findBacklogById(backlog_id);
    }

    @GetMapping("/{projectId}/{backlogId}")
    public ResponseEntity<?> getProjectBacklogBySequence(@PathVariable String projectId, @PathVariable String backlogId) {
        ProjectTask projectTask = projectTaskService.findProjectTaskBySequence(projectId, backlogId);

        return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
    }

    @PutMapping("/{projectId}/{backlogId}")
    public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult result, @PathVariable String projectId, @PathVariable String backlogId) {
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if (errorMap != null) return errorMap;

        ProjectTask updatedTask = projectTaskService.updateBySequence(projectTask, projectId, backlogId);

        return new ResponseEntity(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}/{backlogId}")
    public ResponseEntity<?> deleteProjectTask(@PathVariable String projectId, @PathVariable String backlogId) {
        projectTaskService.deleteBySequence(projectId, backlogId);

        return new ResponseEntity(HttpStatus.OK);
    }

}
