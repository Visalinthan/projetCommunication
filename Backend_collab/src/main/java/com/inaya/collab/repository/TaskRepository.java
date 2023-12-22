package com.inaya.collab.repository;

import com.inaya.collab.model.Dashboard;
import com.inaya.collab.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByDashboardId(Long dashboardId);
}
