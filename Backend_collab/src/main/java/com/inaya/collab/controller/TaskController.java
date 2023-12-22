package com.inaya.collab.controller;

import com.inaya.collab.exceptions.EntityNotFoundException;
import com.inaya.collab.model.Task;
import com.inaya.collab.model.TaskStatus;
import com.inaya.collab.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        try {
            Task createdTask = taskService.createTask(task);
            return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        try {
            Task task = taskService.getTaskById(taskId);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/dashboard/{dashboardId}")
    public ResponseEntity<List<Task>> getAllTasksByDashboard(@PathVariable Long dashboardId) {
        List<Task> tasks = taskService.getAllTasksByDashboard(dashboardId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Task> updateTask(@RequestBody Task updatedTask) {
        try {
            Task task = taskService.updateTask(updatedTask);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/status/{taskId}")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long taskId, @RequestBody TaskStatus status) {
        try {
            Task task = taskService.getTaskById(taskId);
            task.setStatus(status);
            Task taskUpdate = taskService.updateTask(task);
            return new ResponseEntity<>(taskUpdate, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        try {
            taskService.deleteTask(taskId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}