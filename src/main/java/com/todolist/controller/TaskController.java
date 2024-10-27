package com.todolist.controller;

import com.todolist.entities.Task;
import com.todolist.mapDTO.TaskDTO;
import com.todolist.repository.TaskRepository;
import com.todolist.mapDTO.MapperDTO;
import com.todolist.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private TaskService service;

    @Autowired
    private MapperDTO mapper;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskDTO taskDTO, HttpServletRequest request) throws Exception {
        Task result = service.create(taskDTO, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    @GetMapping("/")
    public List<Task> list(HttpServletRequest request){
       return service.list(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TaskDTO taskModel, @PathVariable Integer id, HttpServletRequest request) throws Exception {
        Task resultUpdate = service.update(taskModel, id, request);
        return ResponseEntity.ok().body(resultUpdate);

    }
}
