package com.inaya.collab.controller;

import com.inaya.collab.exceptions.EntityNotFoundException;
import com.inaya.collab.model.Dashboard;
import com.inaya.collab.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboards")
public class DashboardController {

    private final DashboardService dashboardService;

    @Autowired
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @PostMapping
    public ResponseEntity<Dashboard> createDashboard(@RequestBody Dashboard dashboard) {
        try {
            Dashboard createdDashboard = dashboardService.createDashboard(dashboard);
            return new ResponseEntity<>(createdDashboard, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{dashboardId}")
    public ResponseEntity<Dashboard> getDashboardById(@PathVariable Long dashboardId) {
        try {
            Dashboard dashboard = dashboardService.getDashboardById(dashboardId);
            return new ResponseEntity<>(dashboard, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Dashboard>> getAllDashboards() {
        List<Dashboard> dashboards = dashboardService.getAllDashboards();
        return new ResponseEntity<>(dashboards, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Dashboard>> getAllDashboardsByUser(@PathVariable Long userId) {
        List<Dashboard> dashboards = dashboardService.getAllDashboardsByUserId(userId);
        return new ResponseEntity<>(dashboards, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Dashboard> updateDashboard(@RequestBody Dashboard updatedDashboard) {
        try {
            Dashboard dashboard = dashboardService.updateDashboard(updatedDashboard);
            return new ResponseEntity<>(dashboard, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{dashboardId}")
    public ResponseEntity<Void> deleteDashboard(@PathVariable Long dashboardId) {
        try {
            dashboardService.deleteDashboard(dashboardId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
