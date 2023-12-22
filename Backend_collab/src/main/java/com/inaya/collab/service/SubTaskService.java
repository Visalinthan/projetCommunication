package com.inaya.collab.service;

import com.inaya.collab.exceptions.EntityNotFoundException;
import com.inaya.collab.model.Sub_Task;
import com.inaya.collab.repository.SubTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SubTaskService {

    private final SubTaskRepository subTaskRepository;

    @Autowired
    public SubTaskService(SubTaskRepository subTaskRepository) {
        this.subTaskRepository = subTaskRepository;
    }

    // Create
    @Transactional
    public Sub_Task createSubTask(Sub_Task subTask) {
        if (subTask.getId() != null) {
            throw new IllegalArgumentException("SubTask ID must be null for creation.");
        }
        return subTaskRepository.save(subTask);
    }

    // Read
    public Sub_Task getSubTaskById(Long taskId) {
        return subTaskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("SubTask not found with ID: " + taskId));
    }

    public List<Sub_Task> getAllSubTasks() {
        return subTaskRepository.findAll();
    }

    // Update
    @Transactional
    public Sub_Task updateSubTask(Sub_Task updatedSubTask) {
        if (subTaskRepository.existsById(updatedSubTask.getId())) {
            return subTaskRepository.save(updatedSubTask);
        } else {
            throw new EntityNotFoundException("SubTask not found with ID: " + updatedSubTask.getId());
        }
    }

    // Delete
    @Transactional
    public void deleteSubTask(Long taskId) {
        if (subTaskRepository.existsById(taskId)) {
            subTaskRepository.deleteById(taskId);
        } else {
            throw new EntityNotFoundException("SubTask not found with ID: " + taskId);
        }
    }
}
