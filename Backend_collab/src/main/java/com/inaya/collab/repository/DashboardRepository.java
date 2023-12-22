package com.inaya.collab.repository;

import com.inaya.collab.model.Dashboard;
import com.inaya.collab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DashboardRepository extends JpaRepository<Dashboard,Long> {
    List<Dashboard> findByMembers(User user);
}
