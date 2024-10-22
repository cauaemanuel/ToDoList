package com.todolist.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity(name = "tb_tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    @Column(length = 50)
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private Integer userID;

    public void setTitle(String title) throws RuntimeException{
        if(title.length() > 50){
            throw new RuntimeException("O campo title deve conter no maximo 50 characteres");
        }
        this.title = title;
    }
}
