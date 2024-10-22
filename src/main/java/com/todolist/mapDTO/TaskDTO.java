package com.todolist.mapDTO;

import java.time.LocalDateTime;

public record TaskDTO(String description, String title, LocalDateTime startAt,
                      LocalDateTime endAt, String priority,  LocalDateTime createdAt,Integer userID) {
}
