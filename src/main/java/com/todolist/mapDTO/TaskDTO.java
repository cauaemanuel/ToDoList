package com.todolist.mapDTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDTO{
        private String description;
        private String title;
        private LocalDateTime startAt;
        private LocalDateTime endAt;
        private String priority;
        private LocalDateTime createdAt;
        private Integer userID;
}
