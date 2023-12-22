package com.inaya.collab.service;

import com.inaya.collab.exceptions.EntityNotFoundException;
import com.inaya.collab.model.Dashboard;
import com.inaya.collab.model.User;
import com.inaya.collab.repository.DashboardRepository;
import com.inaya.collab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DashboardService {

    private final DashboardRepository dashboardRepository;
    private final UserRepository userRepository;

    @Autowired
    public DashboardService(DashboardRepository dashboardRepository, UserRepository userRepository) {
        this.dashboardRepository = dashboardRepository;
        this.userRepository = userRepository;
    }

    // Create
    public Dashboard createDashboard(Dashboard dashboard) {
        if (dashboard.getId() != null) {
            throw new IllegalArgumentException("Dashboard ID must be null for creation.");
        }
        return dashboardRepository.save(dashboard);
    }

    // Read
    public Dashboard getDashboardById(Long dashboardId) {
        return dashboardRepository.findById(dashboardId)
                .orElseThrow(() -> new EntityNotFoundException("Dashboard not found with ID: " + dashboardId));
    }

    public List<Dashboard> getAllDashboards() {
        return dashboardRepository.findAll();
    }

    public List<Dashboard> getAllDashboardsByUserId(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return dashboardRepository.findByMembers(user);
        } else {
            return Collections.emptyList();
        }
    }

    // Update
    public Dashboard updateDashboard(Dashboard updatedDashboard) {
        if (dashboardRepository.existsById(updatedDashboard.getId())) {
            return dashboardRepository.save(updatedDashboard);
        } else {
            throw new EntityNotFoundException("Dashboard not found with ID: " + updatedDashboard.getId());
        }
    }

    // Delete
    public void deleteDashboard(Long dashboardId) {
        if (dashboardRepository.existsById(dashboardId)) {
            dashboardRepository.deleteById(dashboardId);
        } else {
            throw new EntityNotFoundException("Dashboard not found with ID: " + dashboardId);
        }
    }
}

