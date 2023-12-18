package com.inaya.collab.service;

import com.inaya.collab.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    TaskRepository taskRepo;

    public TaskService(TaskRepository taskRepo) {
        this.taskRepo = taskRepo;
    }


}
