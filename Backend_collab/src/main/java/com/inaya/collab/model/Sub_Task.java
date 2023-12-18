package com.inaya.collab.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn( name = "task_id" )
public class Sub_Task extends Task{
    private String sub_title;
    private String description;
}
