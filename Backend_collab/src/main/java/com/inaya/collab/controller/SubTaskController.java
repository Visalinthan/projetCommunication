package com.inaya.collab.controller;

import com.inaya.collab.exceptions.EntityNotFoundException;
import com.inaya.collab.model.Sub_Task;
import com.inaya.collab.service.SubTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subtasks")
public class SubTaskController {

    private final SubTaskService subTaskService;

    @Autowired
    public SubTaskController(SubTaskService subTaskService) {
        this.subTaskService = subTaskService;
    }

    @PostMapping
    public ResponseEntity<Sub_Task> createSubTask(@RequestBody Sub_Task subTask) {
        try {
            Sub_Task createdSubTask = subTaskService.createSubTask(subTask);
            return new ResponseEntity<>(createdSubTask, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Sub_Task> getSubTaskById(@PathVariable Long taskId) {
        try {
            Sub_Task subTask = subTaskService.getSubTaskById(taskId);
            return new ResponseEntity<>(subTask, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Sub_Task>> getAllSubTasks() {
        List<Sub_Task> subTasks = subTaskService.getAllSubTasks();
        return new ResponseEntity<>(subTasks, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Sub_Task> updateSubTask(@RequestBody Sub_Task updatedSubTask) {
        try {
            Sub_Task subTask = subTaskService.updateSubTask(updatedSubTask);
            return new ResponseEntity<>(subTask, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteSubTask(@PathVariable Long taskId) {
        try {
            subTaskService.deleteSubTask(taskId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
