package com.todolist.service;

import com.todolist.entities.Task;
import com.todolist.mapDTO.MapperDTO;
import com.todolist.mapDTO.TaskDTO;
import com.todolist.repository.TaskRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private MapperDTO map;

    public Task create(TaskDTO taskdto, HttpServletRequest request) throws Exception {

        var iduser = request.getAttribute("userID");
        taskdto.setUserID((Integer) iduser);
        Task task = map.taskDTOtoTask(taskdto);

        LocalDateTime currentDate = LocalDateTime.now();

        if(currentDate.isAfter(task.getStartAt()) || currentDate.isAfter(task.getEndAt())){
            throw new RuntimeException("A data de inicio / data de termino deve ser maior que a atual");
        }
        if(task.getStartAt().isAfter(task.getEndAt())){
            throw new RuntimeException("A data de inicio deve ser menor que a data de termino");
        }

        Task ResultSaveTask = repository.save(task);
        return ResultSaveTask;

    }


    public List<Task> list(HttpServletRequest request){
        List<Task> tasks = repository.findByUserID((Integer)request.getAttribute("userID"));
        return tasks;
    }

    public Task update(TaskDTO taskModel, Integer id, HttpServletRequest request) throws Exception {
        Task task = repository.findById(id).orElse(null);

        if(task == null){
            throw new RuntimeException("tarefa esta vazia");
        }

        var idUser = request.getAttribute("userID");

        if(!task.getUserID().equals(idUser)){
            throw new RuntimeException( "Usuario nao tem permissao para altura essa tarefa");
        }

        Task saveTask = map.atualizarTask(taskModel, task);
        Task result = repository.save(saveTask);

        return result;
    }
}
