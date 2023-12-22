package com.inaya.collab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private String dateCreation;
    private String dateLimit;
    private String status;
    private String priority;
    private Long dashboardId;
    private Long userId;
}
