package com.todolist.controller;

import com.todolist.entities.Task;
import com.todolist.mapDTO.TaskDTO;
import com.todolist.repository.TaskRepository;
import com.todolist.mapDTO.MapperDTO;
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
    private MapperDTO mapper;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody Task task, HttpServletRequest request){
        var iduser = request.getAttribute("userID");

        LocalDateTime currentDate = LocalDateTime.now();

        if(currentDate.isAfter(task.getStartAt()) || currentDate.isAfter(task.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de inicio / data de termino deve ser maior que a atual");
        }

        if(task.getStartAt().isAfter(task.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de inicio deve ser menor que a data de termino");
        }

        task.setUserID((Integer) iduser);
        Task varTask = repository.save(task);
        return ResponseEntity.status(HttpStatus.OK).body(varTask);

    }

    @GetMapping("/")
    public List<Task> list(HttpServletRequest request){
        List<Task> tasks = repository.findByUserID((Integer)request.getAttribute("userID"));
        return tasks;
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TaskDTO taskModel, @PathVariable Integer id, HttpServletRequest request) throws Exception {
        Task task = repository.findById(id).orElse(null);

        if(task == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("tarefa esta vazia");
        }

        var idUser = request.getAttribute("userID");

        if(!task.getUserID().equals(idUser)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Usuario nao tem permissao para altura essa tarefa");
        }

        Task saveTask = mapper.atualizarTask(taskModel, task);

        Task result = repository.save(saveTask);

        return ResponseEntity.ok().body(result);
    }
}
