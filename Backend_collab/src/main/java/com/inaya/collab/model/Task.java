package com.inaya.collab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance( strategy = InheritanceType.JOINED )
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String dateCreation;
    private String dateLimit;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private String priority;

    @JoinColumn(name = "dashboard_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Dashboard dashboard;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
}
