package com.inaya.collab.service;

import com.inaya.collab.exceptions.EntityNotFoundException;
import com.inaya.collab.model.Dashboard;
import com.inaya.collab.model.Task;
import com.inaya.collab.repository.DashboardRepository;
import com.inaya.collab.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final DashboardRepository dashboardRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, DashboardRepository dashboardRepository) {
        this.taskRepository = taskRepository;
        this.dashboardRepository = dashboardRepository;
    }

    // Create
    public Task createTask(Task task) {
        if (task.getId() != null) {
            throw new IllegalArgumentException("Task ID must be null for creation.");
        }
        return taskRepository.save(task);
    }

    // Read
    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with ID: " + taskId));
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getAllTasksByDashboard(Long dashboardId) {
        Dashboard dashboard = dashboardRepository.findById(dashboardId).orElse(null);
        if (dashboard != null) {
            return taskRepository.findByDashboardId(dashboardId);
        } else {
            return Collections.emptyList();
        }
    }

    // Update
    public Task updateTask(Task updatedTask) {
        if (taskRepository.existsById(updatedTask.getId())) {
            return taskRepository.save(updatedTask);
        } else {
            throw new EntityNotFoundException("Task not found with ID: " + updatedTask.getId());
        }
    }

    // Delete
    public void deleteTask(Long taskId) {
        if (taskRepository.existsById(taskId)) {
            taskRepository.deleteById(taskId);
        } else {
            throw new EntityNotFoundException("Task not found with ID: " + taskId);
        }
    }

}

